package com.hcl.usf.service;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicVotes;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
/**
 * @author intakhabalam.s@hcl.com
 */
@Service
public class JiraClientService {
	private final static Logger LOGGER = Logger.getLogger("JiraClientService");

    private JiraRestClient restClient;
    @Autowired
    Environment env;
 
    public void initClient() {
      restClient = getJiraRestClient();
    }
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
       //  JiraClientService myJiraClient = new JiraClientService("x3o6026", "", "https://jira.usfood.com");
       /*
         final String issueKey = myJiraClient.createIssue("ECR46", 1L, "Issue created from STOS Automation Tools");
         myJiraClient.updateIssueDescription(issueKey, "Description from my Jira Client, Which is creating by STOS application for testing purpose");
         Issue issue = myJiraClient.getIssue(issueKey);
         myJiraClient.addAttachement(issue,"C:\\Workspace\\USFood_Workspace\\stos\\reports\\STOS_Reports.html");
         System.out.println(issue.getDescription());
        //myJiraClient.voteForAnIssue(issue);
         System.out.println(myJiraClient.getTotalVotesCount(issueKey));
         myJiraClient.addComment(issue, "This is comment from my Jira Client, Which is creating by STOS application for testing purpose");
         List<Comment> comments = myJiraClient.getAllComments(issueKey);
         comments.forEach(c -> System.out.println(c.getBody()));
        */
       // myJiraClient.deleteIssue("ECR46-611", true);
       // myJiraClient.restClient.close();
    }
    
    
   /**
    * @param ticketKey
    */
   public void deleteIssue(String ticketKey) {
	     try {
	    	 
	    	 deleteIssue(ticketKey, true);
		} catch (Exception e) {
			LOGGER.error(ticketKey+" deleted jira ticket "+e.getMessage());
		}finally {
			try {
				restClient.close();
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	    LOGGER.info("Deleted jira ticket");
   }

    /***
     * 
     * @param projectKey
     * @param bugType
     * @param header
     * @param description
     * @param commments
     * @param attachmentPath
     * @param filePath 
     */
	public  String createIssueOnJira(String projectKey,
			Long bugType, String header, String description,
			String commments, String attachmentPath) {
               String issueKey="";
		try {
			 initClient();
			 issueKey = createIssue(projectKey, bugType, header);
			 updateIssueDescription(issueKey, description);
			 Issue issue = getIssue(issueKey);
			 LOGGER.info(issue.getDescription());
			// myJiraClient.voteForAnIssue(issue);
			 LOGGER.info(getTotalVotesCount(issueKey));

			if (commments != null && !commments.isEmpty()) {
				addComment(issue, commments);
			}
			try {
				Thread.sleep(6000);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if (attachmentPath != null && !attachmentPath.isEmpty()) {
				addAttachement(issue, attachmentPath);
			}
			//restClient.close();
		} catch (Exception e) {
			 LOGGER.error("create issue error {} :" + e.fillInStackTrace());
		}finally {
			try {
				restClient.close();
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		return issueKey;
	}
    /***
     * 
     * @param projectKey  {@link String}
     * @param issueType  {@link Long}
     * @param issueSummary  {@link String}
     * @return {@link String}
     */
    private String createIssue(String projectKey, Long issueType, String issueSummary) {

        IssueRestClient issueClient = restClient.getIssueClient();

        IssueInput newIssue = new IssueInputBuilder(projectKey, issueType, issueSummary).build();

        return issueClient.createIssue(newIssue).claim().getKey();
    }

    private Issue getIssue(String issueKey) {
        return restClient.getIssueClient().getIssue(issueKey).claim();
    }
 
    @SuppressWarnings("unused")
	private void voteForAnIssue(Issue issue) {
        restClient.getIssueClient().vote(issue.getVotesUri()).claim();
    }

    private int getTotalVotesCount(String issueKey) {
        BasicVotes votes = getIssue(issueKey).getVotes();
        return votes == null ? 0 : votes.getVotes();
    }

    private void addComment(Issue issue, String commentBody) {
        restClient.getIssueClient().addComment(issue.getCommentsUri(), Comment.valueOf(commentBody));
    }
    
    private void addAttachement(Issue issue,String filePath) {
    	FileInputStream fileStreamPath;
		try {
			initClient();
			LOGGER.info("File path for attchement :: "+filePath );
			LOGGER.info("File name:: "+Paths.get(filePath).toFile().getName());
			fileStreamPath = new FileInputStream(filePath);
			restClient.getIssueClient().addAttachment(issue.getAttachmentsUri(), fileStreamPath, Paths.get(filePath).toFile().getName()).claim();
		} catch (FileNotFoundException e) {
			LOGGER.error("File not found for attachement {} "+e);
		}

    }
    
    @SuppressWarnings("unused")
	private List<Comment> getAllComments(String issueKey) {
        return StreamSupport.stream(getIssue(issueKey).getComments().spliterator(), false)
          .collect(Collectors.toList());
    }

    private void updateIssueDescription(String issueKey, String newDescription) {
    	initClient();
        IssueInput input = new IssueInputBuilder().setDescription(newDescription).build();
        restClient.getIssueClient().updateIssue(issueKey, input).claim();
    }

    private void deleteIssue(String issueKey, boolean deleteSubtasks) {
    	initClient();
        restClient.getIssueClient().deleteIssue(issueKey, deleteSubtasks).claim();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
          .createWithBasicHttpAuthentication(getJiraUri(), env.getProperty("jira.username"), env.getProperty("jira.password"));
    }

    private URI getJiraUri() {
        return URI.create(env.getProperty("jira.url"));
    }
}
