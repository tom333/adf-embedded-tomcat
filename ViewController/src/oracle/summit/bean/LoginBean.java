package oracle.summit.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.event.DialogEvent;

public class LoginBean {

    String _username = null;
    String _password = null;

    public LoginBean() {
        super();
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getPassword() {
        return _password;
    }

    public void onLoginAction(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            doLogin();
        } else {
            //cancel, do nothing
        }
    }

    private String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (_username == null || _password == null) {
            showError("Invalid credentials", "An incorrect username or password was specified.", null);
        } else {
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            try {
                request.login(_username, _password);
                _username = null;
                _password = null;

                String loginUrl =
                    ectx.getRequestContextPath() + "/adfAuthentication?success_url=/faces" +
                    ctx.getViewRoot().getViewId();
                redirect(loginUrl);
            } catch (ServletException fle) {
                showError("ServletException", "Login failed. Please verify the username and password and try again.",
                          null);
            }
        }
        return null;
    }

    private void redirect(String forwardUrl) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        try {
            ectx.redirect(forwardUrl);
        } catch (IOException e) {
            showError("IOException", "An error occurred during redirecting. Please consult logs for more information.",
                      e);
        }
    }

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        HttpServletRequest httpRequest = (HttpServletRequest) ectx.getRequest();
        try {
            httpRequest.logout();
            HttpSession session = httpRequest.getSession(false); // Servlet 3.0 logout, bug 16556686
            if (session != null) {
                session.invalidate();
            }
            String logoutUrl = ectx.getRequestContextPath() + "/faces" + ctx.getViewRoot().getViewId();
            redirect(logoutUrl);
        } catch (ServletException e) {
            showError("ServletException", "An error occurred during logout. Please consult logs for more information.",
                      e);
        }
        return null;
    }

    private void showError(String errType, String message, Exception e) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errType, message);
        FacesContext.getCurrentInstance().addMessage("d2:it35", msg);
        if (e != null) {
            e.printStackTrace();
        }
    }
}
