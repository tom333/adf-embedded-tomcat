package oracle.summit.backing;


import java.util.ArrayList;
import java.util.List;


import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.util.ResetUtils;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class CustomersBackingBean {
    private RichSelectOneChoice creditRating;
    private RichShowDetailItem sdi3;
    private RichShowDetailItem sdi4;


    public CustomersBackingBean() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setCreditRating(RichSelectOneChoice creditRating) {
        this.creditRating = creditRating;
    }

    public RichSelectOneChoice getCreditRating() {
        return creditRating;
    }

    public List getStarRating() {
        
        ArrayList al = new ArrayList();
        if (getCreditRating() == null ||
            getCreditRating().getValue() == null) {
            return al;
        }
        //So first thing is get a numeric value of the credit rating
        //This will just be the position in the list (1=Excellent, 4=Poor)

        int numrating;
        if (getCreditRating().getValue() == null) {
            numrating = -1;
        }

        else {
            numrating =
                    4 - Integer.parseInt(getCreditRating().getValue().toString());
        }


        //Now fill the array list with stars

        for (int i = 0; i < numrating; i++) {
            al.add("star_ena.png");
        }
        return al;
    }

    public void setSdi3(RichShowDetailItem sdi3) {
        this.sdi3 = sdi3;
    }

    public RichShowDetailItem getSdi3() {
        return sdi3;
    }

    public void setSdi4(RichShowDetailItem sdi4) {
        this.sdi4 = sdi4;
    }

    public RichShowDetailItem getSdi4() {
        return sdi4;
    }

    public void switchEditTab(ActionEvent actionEvent) {
        // Add event code here...
        //This method is called when editing an order and switched to the edit tab
        getSdi3().setDisclosed(false);
        getSdi4().setDisclosed(true);

    }

    public void createNewOrder() {
        // Add event code here...
        //This method is called when creating a new order to create the record and switch the tab
        getSdi3().setDisclosed(false);
        getSdi4().setDisclosed(true);
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            (OperationBinding)bindings.getOperationBinding("CreateInsert");
        operationBinding.execute();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(getSdi4());

    }

    public void browseTabDisclosureListener(DisclosureEvent disclosureEvent) {
        // Add event code here...
        //This method is called to reset the view criteria when looking at the browse tab.
        //This means that the tree control will show all possible countries/customers
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = (OperationBinding)bindings.getOperationBinding("unsetCustomerViewCriteria");
        operationBinding.execute();
    }

    public void deleteOrderConfirmation(DialogEvent dialogEvent) {
        // Add event code here...
        //This is the confirmation dialog for deleting an order
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
          BindingContainer bindings = getBindings();
          OperationBinding operationBinding =
              (OperationBinding)bindings.getOperationBinding("Delete");
          operationBinding.execute();
            
        }
    }

    public String onRollback() {
        if ( sdi3 != null ) {
            // task flow needs to be refreshed on rollback
            ResetUtils.reset( sdi3 );
        }
        return null;
    }
}
