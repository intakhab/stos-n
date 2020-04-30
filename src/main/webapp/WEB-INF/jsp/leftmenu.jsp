<div class="sidebar-menu" style="height: 100%; position: fixed;">
	<div class="logo">
		<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span>
		  </a> <a href="#"> <span id="logo"></span>
		</a>
	</div>
	<div class="menu">
		<ul id="menu">
			<li id="menuHomeId"><a href="home"><i
					class="fa fa-tachometer"></i><span>Home</span></a></li>
			<li id="automationId"><a href="#"><i class="fa fa-cogs"></i><span>Automation</span><span
					class="fa fa-angle-right" style="float: right"></span></a>
				<ul>
					<li><a href="registertc">Register TC</a></li>
					<li><a href="grouptc">Group TC&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<li><a href="showregisteredtc">Run TC&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li id="systemId"><a href="#"><i class="fa fa-cog"></i><span>System</span>
			     <span class="fa fa-angle-right" style="float: right"></span></a>
				<ul id="menu-academico-sub">
				   <li id="menu-academico-avaliacoes">
				     <a href="settings">Setting</a></li>
				   <li id="menu-academico-avaliacoes"><a href="h2database">Database</a></li>
				</ul>
			</li>
			<!--  -->
			<li id="utility"><a href="#"><i class="fa fa-cog"></i> <span>Utility</span>
			    <span class="fa fa-angle-right" style="float: right"></span></a>
				<ul id="menu-academico-sub">
					<li id="menu-academico-avaliacoes"><a href="datatables">
							Ecom Datasheet</a></li>
				<li id="menu-academico-avaliacoes"><a href="ecomdb">Ecom&nbsp;DB&nbsp;&nbsp;</a></li>
				<li id="apiId"><a href="apirun"><span>API Run</span> </a></li>
				</ul>
			</li>
			<!--  -->
			<li id="reportsId"><a href="showreports"><i
					class="fa fa-book nav_icon"></i> <span>Reports</span> </a></li>
			<li id="quickrunId"><a href="showregisteredtc"><i
					class="fa fa-book fa-cog"></i> <span>Quick Run</span> </a></li>
			
			${node}	
		</ul>
	</div>
</div>