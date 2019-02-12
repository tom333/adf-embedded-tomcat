package oracle.summit.backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;


public class ShuttleBean {
    
    private List<oracle.jbo.domain.Number> _selectedProducts = new ArrayList<oracle.jbo.domain.Number>();
    
    public ShuttleBean()
    {
    }

    public void setSelectedProducts(List<oracle.jbo.domain.Number> _allocatedRoles) {
        this._selectedProducts = _allocatedRoles;
    }

    public List<oracle.jbo.domain.Number> getSelectedProducts() {
        return _selectedProducts;
    }

    public void onAddMultipleProducts(ActionEvent actionEvent) {        
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding createMethod = dcBindings.getOperationBinding("CreateInsert");
        
        if ( createMethod != null && _selectedProducts.size() > 0 ) {
            DCIteratorBinding iterator = dcBindings.findIteratorBinding("ItemsForOrderIterator");
            for ( oracle.jbo.domain.Number productId : _selectedProducts )
            {
                createMethod.execute();
                iterator.getCurrentRow().setAttribute("ProductId", productId );
            }
        }
    }
}
