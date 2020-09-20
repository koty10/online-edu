package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.service.DemoService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "demoBackingBean")
@RequestScoped
public class DemoBackingBean {

    @EJB
    private DemoService demoService;

    /**
     * Creates a new instance of DemoBackingBean
     */
    public DemoBackingBean() {
    }

    public String getGreetings() {
        return "Hello from DemoBackingBean";
    }

    public String getGreetingsFromService() {
        return demoService.getGreetings();
    }
}
