<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>
<div class="container" >	
		<div class="container-fluid">
		</br></br></br></br>
				
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
 		 <!-- Indicators -->
  		<ol class="carousel-indicators">
    		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    		<li data-target="#myCarousel" data-slide-to="1"></li>
   			<li data-target="#myCarousel" data-slide-to="2"></li>
    		<li data-target="#myCarousel" data-slide-to="3"></li>
 		 </ol>

  		<!-- Wrapper for slides -->
 		 <div class="carousel-inner" role="listbox">
    		<div class="item active img-responsive img-rounded">
      		<img src="LookAndFeel/css/car/car0.jpg" alt="Chania">
   		 </div>

    	<div class="item img-responsive img-rounded">
     		 <img src="LookAndFeel/css/car/car1.jpg" alt="Chania">
    	</div>

    	<div class="item img-responsive img-rounded">
     	 <img src="LookAndFeel/css/car/car2.jpg" alt="Flower">
    	</div>

   		<div class="item img-responsive img-rounded">
     	 <img src="LookAndFeel/css/car/car3.jpg" alt="Flower">
   	 	</div>
  		</div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

		
		<div class="row">
			<div class="col-sm-4" style="background-color:transparent;">	
				
				</br></br>
					<img src="LookAndFeel/css/about.jpg" class="img-circle" alt="Cinque Terre" width="350" height="260"> 
				</br></br>
				
			</div>		
			<div class="col-sm-8 tab-content"  >			
				<h3><b>Ilic Dragoljub, RA44/2011</b></h3>
				
				</br></br>
				<h2><i>Biography</i></h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras hendrerit dui et lectus blandit, sed fringilla leo viverra.
				 Vivamus gravida sodales est in finibus. Donec sit amet erat tellus. Maecenas placerat arcu nibh, eu scelerisque mi imperdiet in.
				 Mauris viverra arcu at porta hendrerit. Integer accumsan dolor in lorem sagittis vestibulum. Morbi diam tellus, egestas et nisi 
				 pellentesque, molestie laoreet lorem. Nam ut pretium nulla. Fusce mattis urna ut congue scelerisque. Duis facilisis nisl hendrerit
				 condimentum varius. Phasellus interdum nec ipsum vel dictum. Praesent non ligula consectetur, volutpat erat quis, hendrerit eros.</p>
				 
				 <p>Curabitur at dignissim lacus, eu pulvinar diam. Nunc eleifend, dolor vel rutrum varius, justo mi mollis mauris, et ornare neque
				 dui non ante. Sed vitae rutrum felis, sed iaculis eros. Sed vehicula bibendum tempor. Curabitur molestie cursus ipsum, nec 
				 molestie eros dignissim eu. Aliquam pellentesque mauris vel molestie rhoncus. Curabitur viverra consectetur eros.</p>
				 
				 <p>Sed laoreet dignissim quam dictum malesuada. Donec non ultricies arcu, nec luctus orci. Ut at placerat odio. Duis sapien dui,
				 malesuada nec leo in, cursus gravida erat. In placerat id felis quis placerat. Morbi et consequat orci. Proin euismod elementum
				 erat, id fermentum felis vehicula imperdiet. Suspendisse vitae bibendum nisl. Praesent auctor augue sit amet eros laoreet placerat.</p>
				  
				<p>Duis nulla nibh, aliquet nec diam eu, posuere faucibus dui. Aenean id enim venenatis, imperdiet justo at, rhoncus metus.
				 Aliquam hendrerit justo quis dapibus rhoncus. Nulla ultrices libero non sapien pretium facilisis. Maecenas nec sapien luctus,
				 laoreet erat eu, molestie libero. Suspendisse et enim condimentum, bibendum neque a, suscipit ex. Nam a mauris id sem rhoncus
				 egestas. Aliquam quis dolor tincidunt, convallis nisl vestibulum, interdum arcu. Nulla commodo sollicitudin lacus vel aliquet.
				 Mauris venenatis tortor dui, et elementum tellus consectetur ut. Maecenas vel sem ante. Etiam vel lacinia nibh, ac volutpat 
				 dio. Aliquam blandit ligula augue, sit amet pretium libero suscipit nec.</p>
				 
				 <p>Duis nulla nibh, aliquet nec diam eu, posuere faucibus dui. Aenean id enim venenatis, imperdiet justo at, rhoncus metus.
				 Aliquam hendrerit justo quis dapibus rhoncus. Nulla ultrices libero non sapien pretium facilisis. Maecenas nec sapien luctus,
				 laoreet erat eu, molestie libero. Suspendisse et enim condimentum, bibendum neque a, suscipit ex. Nam a mauris id sem rhoncus
				 egestas. Aliquam quis dolor tincidunt, convallis nisl vestibulum, interdum arcu. Nulla commodo sollicitudin lacus vel aliquet.
				 Mauris venenatis tortor dui, et elementum tellus consectetur ut. Maecenas vel sem ante. Etiam vel lacinia nibh, ac volutpat 
				 dio. Aliquam blandit ligula augue, sit amet pretium libero suscipit nec.</p>
				 
				 <p>Duis nulla nibh, aliquet nec diam eu, posuere faucibus dui. Aenean id enim venenatis, imperdiet justo at, rhoncus metus.
				 Aliquam hendrerit justo quis dapibus rhoncus. Nulla ultrices libero non sapien pretium facilisis. Maecenas nec sapien luctus,
				 laoreet erat eu, molestie libero. Suspendisse et enim condimentum, bibendum neque a, suscipit ex. Nam a mauris id sem rhoncus
				 egestas. Aliquam quis dolor tincidunt, convallis nisl vestibulum, interdum arcu. Nulla commodo sollicitudin lacus vel aliquet.
				 Mauris venenatis tortor dui, et elementum tellus consectetur ut. Maecenas vel sem ante. Etiam vel lacinia nibh, ac volutpat 
				 dio. Aliquam blandit ligula augue, sit amet pretium libero suscipit nec.</p>
				 
				 <p>Duis nulla nibh, aliquet nec diam eu, posuere faucibus dui. Aenean id enim venenatis, imperdiet justo at, rhoncus metus.
				 Aliquam hendrerit justo quis dapibus rhoncus. Nulla ultrices libero non sapien pretium facilisis. Maecenas nec sapien luctus,
				 laoreet erat eu, molestie libero. Suspendisse et enim condimentum, bibendum neque a, suscipit ex. Nam a mauris id sem rhoncus
				 egestas. Aliquam quis dolor tincidunt, convallis nisl vestibulum, interdum arcu. Nulla commodo sollicitudin lacus vel aliquet.
				 Mauris venenatis tortor dui, et elementum tellus consectetur ut. Maecenas vel sem ante. Etiam vel lacinia nibh, ac volutpat 
				 dio. Aliquam blandit ligula augue, sit amet pretium libero suscipit nec.</p>
				 
				 <p>Duis nulla nibh, aliquet nec diam eu, posuere faucibus dui. Aenean id enim venenatis, imperdiet justo at, rhoncus metus.
				 Aliquam hendrerit justo quis dapibus rhoncus. Nulla ultrices libero non sapien pretium facilisis. Maecenas nec sapien luctus,
				 laoreet erat eu, molestie libero. Suspendisse et enim condimentum, bibendum neque a, suscipit ex. Nam a mauris id sem rhoncus
				 egestas. Aliquam quis dolor tincidunt, convallis nisl vestibulum, interdum arcu. Nulla commodo sollicitudin lacus vel aliquet.
				 Mauris venenatis tortor dui, et elementum tellus consectetur ut. Maecenas vel sem ante. Etiam vel lacinia nibh, ac volutpat 
				 dio. Aliquam blandit ligula augue, sit amet pretium libero suscipit nec.</p>
				
        	</div>
		</div>
		</div>
</div>
			
			
			
	

<jsp:include page="end.jsp"/>
	