package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import utility.AtomicCounter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CountController extends Controller {

    private AtomicCounter counter;
    @Inject
    public CountController(AtomicCounter counter){
         this.counter = counter;
    }
    public Result getCurrentCount() {

        return ok("count: "+counter.getCount());
    }


}
