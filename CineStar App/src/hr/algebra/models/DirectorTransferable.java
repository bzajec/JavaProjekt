/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Bruno
 */
public class DirectorTransferable implements Transferable{
    
    public static final DataFlavor DIRECTOR_FLAVOR = new DataFlavor(Director.class, "Director");

    public static final DataFlavor[] SUPPORTED_FLAVORS = {DIRECTOR_FLAVOR};

    private final Director director;

    public DirectorTransferable(Director director) {
        this.director = director;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DIRECTOR_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(DIRECTOR_FLAVOR)) {
            return director;
        } 
        throw new UnsupportedFlavorException(flavor);
    }
}
