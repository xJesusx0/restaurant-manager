package co.jesus.RestaurantManager.entities;

public class SimpleResponse {
    private String response;

    public SimpleResponse(String response){
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
