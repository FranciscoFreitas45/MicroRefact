package org.gliderwiki.framework.exception;
 import org.gliderwiki.framework.util.FtpReply;
public class FtpException extends Exception{

 private  FtpReply d_reply;

/**
 * Constructs new FtpException with short explaination of the circumstance.
 * The explaination is prepended to the text of the actual reply to form the
 * exception message.
 *
 * @parm reply reply that caused the exception
 * @parm msg cirsumstance of exception
 */
public FtpException(FtpReply reply, String msg) {
    super(msg + ": " + reply.getText());
    d_reply = reply;
}/**
 * Convience constructor; Creates new FtpException using "Unexpected" as the
 * explaination.
 *
 * @parm reply reply that caused the exception
 */
public FtpException(FtpReply reply) {
    this(reply, "Unexpected");
}
public int getType(){
    return d_reply.getType();
}


public FtpReply getReply(){
    return d_reply;
}


public int getCode(){
    return d_reply.getCode();
}


}