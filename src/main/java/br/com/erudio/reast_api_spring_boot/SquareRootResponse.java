package br.com.erudio.reast_api_spring_boot;

public class SquareRootResponse {
    private Double[] data;

    public SquareRootResponse(Double[] data) {
        this.data = data;
    }

    public Double[] getData() {
        Double[] responseData = new Double[data.length];
    
        for(int i = 0; i < data.length; i++) {
            responseData[i] = Math.sqrt(data[i]);
        }
        return responseData;
    }
}
