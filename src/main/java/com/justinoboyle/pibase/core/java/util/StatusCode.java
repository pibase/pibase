package com.justinoboyle.pibase.core.java.util;

/**
 * 
 * @author Programming: Justin (http://www.github.com/ArrayPro) and
 *         Information/Documentation:
 *         http://www.w3.org/Protocols/HTTP/HTRESP.html
 * @see Feel free to use this software in your own programs, but please keep the
 *      credits shown here!
 */
public enum StatusCode {
 
    // Success 2xx
    /**
     * The request was fulfilled.
     */
    OK(200, "OK"),
    /**
     * Following a POST command, this indicates success, but the textual part of
     * the response line indicates the URI by which the newly created document
     * should be known.
     */
    CREATED(201, "CREATED"),
    /**
     * The request has been accepted for processing, but the processing has not
     * been completed. The request may or may not eventually be acted upon, as
     * it may be disallowed when processing actually takes place. there is no
     * facility for status returns from asynchronous operations such as this.
     */
    ACCEPTED(202, "Accepted"),
    /**
     * When received in the response to a GET command, this indicates that the
     * returned metainformation is not a definitive set of the object from a
     * server with a copy of the object, but is from a private overlaid web.
     * This may include annotation information about the object, for example.
     */
    PARTIAL_INFORMATION(203, "Partial Information"),
    /**
     * Server has received the request but there is no information to send back,
     * and the client should stay in the same document view. This is mainly to
     * allow input for scripts without changing the document at the same time.
     */
    NO_RESPONSE(204, "No Response"),
 
    // Error 4xx, 5xx
    /**
     * The request had bad syntax or was inherently impossible to be satisfied.
     */
    BAD_REQUEST(400, "Bad request"),
    /**
     * The parameter to this message gives a specification of authorization
     * schemes which are acceptable. The client should retry the request with a
     * suitable Authorization header.
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * The parameter to this message gives a specification of charging schemes
     * acceptable. The client may retry the request with a suitable ChargeTo
     * header.
     */
    PAYMENT_REQURED(402, "PaymentRequired"),
    /**
     * The request is for something forbidden. Authorization will not help.
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * The server has not found anything matching the URI given
     */
    NOT_FOUND(404, "Not found"),
    /**
     * The server encountered an unexpected condition which prevented it from
     * fulfilling the request.
     */
    INTERNAL_ERROR(500, "Internal Error"),
    /**
     * The server does not support the facility required.
     */
    NOT_IMPLEMENTED(501, "Not implemented"),
    /**
     * @deprecated (TO BE DISCUSSED) The server cannot process the request due
     *             to a high load (whether HTTP servicing or other requests).
     *             The implication is that this is a temporary condition which
     *             maybe alleviated at other times.
     */
    SERVICE_TEMPORARILY_OVERLOADED(502, "Service temporarily overloaded"),
    /**
     * @deprecated (TO BE DISCUSSED) This is equivalent to Internal Error 500,
     *             but in the case of a server which is in turn accessing some
     *             other service, this indicates that the respose from the other
     *             service did not return within a time that the gateway was
     *             prepared to wait. As from the point of view of the clientand
     *             the HTTP transaction the other service is hidden within the
     *             server, this maybe treated identically to Internal error 500,
     *             but has more diagnostic value.
     * @see Note: The 502 and 503 codes are new and for discussion, September
     *      19, 1994
     */
    GATEWAY_TIMEOUT(503, "Gateway timeout"),
 
    // Redirection 3xx
    /**
     * The data requested has been assigned a new URI, the change is permanent.
     * (N.B. this is an optimisation, which must, pragmatically, be included in
     * this definition. Browsers with link editing capabiliy should
     * automatically relink to the new reference, where possible)
     * 
     * @see The response contains one or more header lines of the form
     *      "URI: <url> String CrLf" Which specify alternative addresses for the
     *      object in question. The String is an optional comment field. If the
     *      response is to indicate a set of variants which each correspond to
     *      the requested URI, then the multipart/alternative wrapping may be
     *      used to distinguish different sets
     */
    MOVED(301, "Moved"),
    /**
     * The data requested actually resides under a different URL, however, the
     * redirection may be altered on occasion (when making links to these kinds
     * of document, the browser should default to using the Udi of the
     * redirection document, but have the option of linking to the final
     * document) as for "Forward".
     * 
     * @see The response format is the same as for Moved.
     */
    FOUND(302, "Found"),
    /**
     * @see Method: <method> <url>
     * @deprecated This status code is to be specified in more detail. For the
     *             moment it is for discussion only. Like the found response,
     *             this suggests that the client go try another network address.
     *             In this case, a different method may be used too, rather than
     *             GET. The body-section contains the parameters to be used for
     *             the method. This allows a document to be a pointer to a
     *             complex query operation. The body may be preceded by the
     *             following additional fields as listed.
     */
    METHOD(303, "Method"),
    /**
     * If the client has done a conditional GET and access is allowed, but the
     * document has not been modified since the date and time specified in
     * If-Modified-Since field, the server responds with a 304 status code and
     * does not send the document body to the client. Response headers are as if
     * the client had sent a HEAD request, but limited to only those headers
     * which make sense in this context. This means only headers that are
     * relevant to cache managers and which may have changed independently of
     * the document's Last-Modified date. Examples include Date , Server and
     * Expires . The purpose of this feature is to allow efficient updates of
     * local cache information (including relevant metainformation) without
     * requiring the overhead of multiple HTTP requests (e.g. a HEAD followed by
     * a GET) and minimizing the transmittal of information already known by the
     * requesting client (usually a caching proxy).
     */
    NOT_MODIFIED(304, "Not Modified");
 
    private int value;
    private String text;
 
    private StatusCode(int value, String text) {
        this.value = value;
        this.text = text;
    }
 
    /**
     * @return the numeric value specified in the constructor
     */
    public int getValue() {
        return value;
    }
 
    /**
     * @return the String value specified in the constructor
     */
    public String getText() {
        return text;
    }
 
    /**
     * @return the formatted String for sending to the client - uses numeric
     *         value and String value in the constructor.
     */
    public String getResponseText() {
        return "HTTP/1.1 " + this.getValue() + " " + this.getText() + "\r\n";
    }
 
}
