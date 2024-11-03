package br.com.erudio.reast_api_spring_boot;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.reast_api_spring_boot.exceptions.UnsupportedMathOperationException;


@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor sete um valor numérico");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = "subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) { 
            throw new UnsupportedMathOperationException("Por favor sete um valor numérico");
        }
        // firula abaixo
        Double numberOneDouble = convertToDouble(numberOne);
        Double numberTwoDouble = convertToDouble(numberTwo);

        if(numberOneDouble >= numberTwoDouble) {
            return numberOneDouble - numberTwoDouble;
        } else {
            return numberTwoDouble - numberOneDouble;
        }
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor sete um valor numérico");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
        
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        
        if(!isNumeric(numberTwo) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor sete um valor numérico");
        }

        Double numberOneDouble = convertToDouble(numberOne);
        Double numberTwoDouble = convertToDouble(numberTwo);

        return numberOneDouble / numberTwoDouble;
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET) 
    public Double average(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor sete um valor numérico");
        }

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping(value = "/square-root/{numberOne}/{numberTwo}")
    public SquareRootResponse squareRoot(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Por favor sete um valor numérico");
        }

        Double[] squareRootArgs = new Double[2];
        squareRootArgs[0] = convertToDouble(numberOne);
        squareRootArgs[1] = convertToDouble(numberTwo);

        return new SquareRootResponse(squareRootArgs);
    }

    private Double convertToDouble(String strNumber) throws Exception {
        if (strNumber == null)
            throw new UnsupportedMathOperationException("A soma não aceita valores nulos");
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number))
            return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) throws Exception {
        if (strNumber == null)
            throw new UnsupportedMathOperationException("O dado que envio não é numérico");
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
