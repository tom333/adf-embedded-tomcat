package oracle.summit.bean;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class WelcomePageBean {
    private RichPopup popupP1;
    private String taskFlowId = "/WEB-INF/flows/emp-reg-task-flow-definition.xml#emp-reg-task-flow-definition";
    private boolean isRegistration;

    public WelcomePageBean() {
    }

    public void setPopupP1(RichPopup popupP1) {
        this.popupP1 = popupP1;
    }

    public RichPopup getPopupP1() {
        return popupP1;
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }
    
    /**
     * @return
     */
    public String employeeregistrationtaskflow() {
        taskFlowId = "/WEB-INF/flows/emp-reg-task-flow-definition.xml#emp-reg-task-flow-definition";
        return null;
    }

    /**
     * @return
     */
    public String customerregistrationtaskflow() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
        return null;
        }
        taskFlowId = "/WEB-INF/flows/edit-customer-task-flow-definition.xml#edit-customer-task-flow-definition";
        return null;
    }

    public void setIsRegistration(boolean isRegistration) {
        this.isRegistration = isRegistration;
    }

    public boolean getIsRegistration() {
        return isRegistration;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String b1_action() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
}
