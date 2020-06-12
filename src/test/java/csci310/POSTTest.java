package csci310;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import csci310.HomePageController;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.atLeast;

public class POSTTest {
	
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	RequestDispatcher rd;
	@Before public void setUp() {
	    MockitoAnnotations.initMocks(this); 
	}

    @Test
    public void testPOSTOnIndex() throws ServletException, IOException {
    
    	when(request.getParameter("searchbar")).thenReturn("Los Angeles");
    	when(request.getRequestDispatcher("Home.jsp")).thenReturn(rd);
    	HomePageController hc = new HomePageController();
	    hc.doPost(request, response);
	    verify(request, atLeast(2)).getParameter("searchbar");

    }
}
