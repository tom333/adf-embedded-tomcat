package oracle.summit.backing;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

public class GeneralInfoBackingBean {
    private RichSelectOneChoice creditRating;

    public GeneralInfoBackingBean() {
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
}
