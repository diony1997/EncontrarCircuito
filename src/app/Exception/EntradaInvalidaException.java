/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Exception;

/**
 *
 * @author Diony
 */
public class EntradaInvalidaException extends Exception {

    public EntradaInvalidaException() {
    }

    public EntradaInvalidaException(String linhaInvalida) {
        super(linhaInvalida);
    }

}
