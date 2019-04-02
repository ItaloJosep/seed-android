package seed_italo.com.br.seeditalo.Service;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by italo.josep on 02/04/2019.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIError {

    private int status;
    private Messages messages;

    public APIError() {
    }

    public APIError(int status, String message) {
        this.status = status;
        this.messages = new Messages("",message);
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Messages{

        private String field;
        private String message;

        public Messages() {
        }

        public Messages(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message != null ? message : "Não foi possível realizar essa ação.";
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
