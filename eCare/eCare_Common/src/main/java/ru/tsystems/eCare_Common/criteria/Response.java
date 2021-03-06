package ru.tsystems.eCare_Common.criteria;


import java.io.Serializable;

/**
 * Response class implements action of answer, to the client.
 * When request received, server do something, and give a response
 * on that request.
 * It is also object which cares information about result of request
 * boolean field problem, if we have an error, client need to know about in,
 * and string message which cares some info.
 */
public class Response implements Serializable{
    private static final long serialVersionUID = 4454945973610760253L;

    private String title;
    private Serializable respBody;
    private boolean isProblem;

    public Response(Serializable obj){
        respBody = obj;
    }

    public Response() {
    }

    public Response(Response response){
        this.isProblem = response.isProblem;
        this.respBody = response.respBody;
        this.title = response.title;
    }

    public Response(String title, Serializable obj, boolean error){
        this.title = title;
        respBody = obj;
        isProblem = error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getRespBody() {
        return respBody;
    }

    public void setRespBody(Serializable respBody) {
        this.respBody = respBody;
    }

    public boolean getIsProblem() {
        return isProblem;
    }

    public void setProblem(boolean isProblem) {
        this.isProblem = isProblem;
    }

    @Override
    public String toString() {
        return "Response{" +
                "title='" + title + '\'' +
                ", respBody=" + respBody +
                ", isProblem=" + isProblem +
                '}';
    }
}
