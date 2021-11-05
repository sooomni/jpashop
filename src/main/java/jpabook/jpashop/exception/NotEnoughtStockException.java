package jpabook.jpashop.exception;

public class NotEnoughStockException() extends RuntimeException{

	public NotEnoughStockException(){}

	public NotEnoughStockException(String message){
		super(message);
	}
	public NotEnoughStockException(String message, Throwable cause){
		supser(message, cause);
	}
	public NotEnoughStockException(Throwable cause){
		super(cause);
	}
	
}