package com.example.demo.dto;

public class GameResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public GameResponse() {}

    public GameResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> GameResponse<T> ok(T data) {
        return new GameResponse<>(true, "Opération réussie", data);
    }

    public static <T> GameResponse<T> ok(String message, T data) {
        return new GameResponse<>(true, message, data);
    }

    public static <T> GameResponse<T> created(String message, T data) {
        return new GameResponse<>(true, message, data);
    }

    public static <T> GameResponse<T> error(String message) {
        return new GameResponse<>(false, message, null);
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}