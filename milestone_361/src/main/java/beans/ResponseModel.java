package beans;

public class ResponseModel {
	
	private int status;
	private String message;
	
	public ResponseModel() {
		status = 0;
		message = "";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
