package com.hcl.usf.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/****
 * @author intakhabalam.s@hcl.com
 */
@XmlRootElement(name = "reports")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocReportsDto {

	@XmlElement(name="test")
	private TestNameDto [] testname;

	public TestNameDto[] getTestname() {
		return testname;
	}

	public void setTestname(TestNameDto[] testname) {
		this.testname = testname;
	}
	
}
