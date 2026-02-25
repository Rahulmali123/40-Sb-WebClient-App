package in.ashokit.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.binding.Book;

@Service
public class BookClient {
	
	public void invokeSaveBook() 
	{
		Book book=new Book();
		
		book.setBookName("Angular");
		book.setBookPrice(450.00);
		
		String apiurl = "https://jsonplaceholder.typicode.com/posts/";

		WebClient client = WebClient.create();

		String resp = client.post() // Post request
				.uri(apiurl) // endpoint url
				.bodyValue(book) // Http Request Body Data
				.retrieve() // retrieve response body
				.bodyToMono(String.class) // bind response data
				.block(); // make it sync

		System.out.println(resp);
	}
	
	public void invokeGetBooksOld() {
		String apiurl = "https://jsonplaceholder.typicode.com/posts/";

		WebClient client = WebClient.create();
/*
		String body = client.get() // get request
				.uri(apiurl) // endpoint url
				.retrieve() // retrieve response body
				.bodyToMono(String.class) // bind response data
				.block(); // make it sync
				
				System.out.println(body);
*/		
		Book[] responseData=client.get() // get request
				.uri(apiurl) // endpoint url
				.retrieve() // retrieve response body
				.bodyToMono(Book[].class) // bind response data
				.block(); // make it sync
		
		for (Book book : responseData) 
		{
			System.out.println(book);
		}		
	}
	
	public void invokeGetBooksAsync() {
	    String apiurl = "https://jsonplaceholder.typicode.com/posts/";

	    WebClient client = WebClient.create();

	    client.get()
	          .uri(apiurl)
	          .retrieve()
	          .bodyToMono(Book[].class)
	          .subscribe(books -> {
	              System.out.println("Response received asynchronously");

	              for (Book book : books) {
	                  System.out.println(book);
	              }
	          });

	    System.out.println("Request sent... main thread not blocked");
	}

}
