package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.stream.Stream;



@RestController
public class GreetingController {
    
    //get the fibonacci https://www.mkyong.com/java/java-fibonacci-examples/
    public int fibo(int number) {
        // System.out.println("Public methods must be called by creating objects");
        int sum = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
		.limit(number)
		.map(t -> t[0])
		.mapToInt(Integer::intValue)
		.sum();
		return sum;
    }

    private static final String template = "Hello hello and Welcome, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping("/")
    public String healthCheck() {
        return "OK!";
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value = "/fibo/{number:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public String getFibonacciSum(
        @PathVariable int number) {
            return "Sum of first " + Integer.toString(number) + " Fibonacci Numbers = " + Integer.toString(fibo(number));
    }
    
}
