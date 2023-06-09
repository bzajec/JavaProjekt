/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Film;
import hr.algebra.models.FilmArchive;
import hr.algebra.models.Genre;
import hr.algebra.models.TableModelFilm;


import hr.algebra.models.User;
import hr.algebra.parsers.rss.Parser;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Bruno
 */
public class AdminForm extends javax.swing.JFrame {

    private Repository repository;
    private List<Film> allParsedFilms;
    private List<Film> allFilmsCurrentlyInDatabase;
    private TableModelFilm tableModelFilm;
    User currentUser;
    private static final String FILENAME = "filmsarchive.xml";
    
    
    /**
     * Creates new form AdminForm
     */
    public AdminForm(User currentUser) throws Exception {
        initComponents();
        this.currentUser = currentUser;
        initRepository();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFilms = new javax.swing.JTable();
        btnUpload = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEditFilms = new javax.swing.JButton();
        btnCreateXml = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblFilms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblFilms);

        btnUpload.setText("Upload films");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete films");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEditFilms.setText("Edit Films");
        btnEditFilms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditFilmsActionPerformed(evt);
            }
        });

        btnCreateXml.setText("Create XML");
        btnCreateXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateXmlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEditFilms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(btnCreateXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditFilms, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(btnCreateXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int retVal = MessageUtils.showConfirmDialog("Data deletion", "Do you wish to delete all data in the database?");
        if (retVal == 0) {
            System.out.println("Deleting all data from database  - start...");

            deleteAllValuesInDatabase();

            deletePictures();
            
            System.out.println("Deleting all data from database  - finished!");
            MessageUtils.showInformationMessage("Success", "All data has been deleted.");
            SwingUtilities.updateComponentTreeUI(tblFilms);

        } else {
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        
        

        initParsedFilms();
        
        try {
            uploadAllFilmsToDatabase(allParsedFilms);
        } catch (Exception ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        initAllFilmsCurrentlyInDatabase();
        MessageUtils.showInformationMessage("Success", "Parsing and uploading successfully completed");
        try {
            initTable();
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) { 
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(this);
        
       
        
        
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnEditFilmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditFilmsActionPerformed
        // TODO add your handling code here:
        dispose();
        EventQueue.invokeLater(() -> {
            try {
                new EditForm().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }//GEN-LAST:event_btnEditFilmsActionPerformed

    private void btnCreateXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateXmlActionPerformed
        // TODO add your handling code here:
        List<Film> xmlFilms = new ArrayList<>();
        if (allFilmsCurrentlyInDatabase != null) {
                    allFilmsCurrentlyInDatabase.forEach(a -> xmlFilms.add(a));

        }
        
        
        
        try {
            JAXBUtils.save(new FilmArchive(xmlFilms),FILENAME);
            MessageUtils.showInformationMessage("Info", "Films saved");
        } catch (JAXBException ex) {
            MessageUtils.showErrorMessage("Error", "Unable to save films");
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnCreateXmlActionPerformed

    /**
     * @param args the command line arguments
     */
   // public static void main(String args[]) {
        /* Set the Nimbus look and feel */
   //     //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     //   try {
       //     for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         //       if ("Nimbus".equals(info.getName())) {
           //         javax.swing.UIManager.setLookAndFeel(info.getClassName());
             //       break;
            //    }
          //  }
       // } catch (ClassNotFoundException ex) {
         //   java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      //  } catch (InstantiationException ex) {
        //    java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       // } catch (IllegalAccessException ex) {
         //   java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      //  } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        //    java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       // }
        //</editor-fold>

        /* Create and display the form */
     //   java.awt.EventQueue.invokeLater(new Runnable() {
       //     public void run() {
                
         //       try {
                    
           //         new AdminForm(1).setVisible(true);
             //   } catch (Exception ex) {
               //     Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
             //   }
          //  }
      //  });
   // }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateXml;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEditFilms;
    private javax.swing.JButton btnUpload;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFilms;
    // End of variables declaration//GEN-END:variables

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }
    
    private void init() {
        try {
            deleteAllValuesInDatabase();
            initAllFilmsCurrentlyInDatabase();
            initTable();

        } catch (Exception ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable error", "Cannot initiate the form");
            System.exit(1);

        }
    }
    
    private void deleteAllValuesInDatabase() {
        allParsedFilms = new ArrayList<>();
        javax.swing.table.TableModel model = tblFilms.getModel();
        

        if (model instanceof TableModel) {
            tableModelFilm = new TableModelFilm(allParsedFilms);
            tblFilms.setModel(tableModelFilm);

        }
        try {
            repository.uspDeleteAllData();
        } catch (Exception ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initAllFilmsCurrentlyInDatabase() {
        try {
            List<Film> films = repository.uspGetAllFilms();
            for (int i = 0; i < films.size(); i++) {
                Film myfilm = films.get(i);
                List<Actor> actorsForThisFilm = repository.uspGetAllActorsForFilm(myfilm.getId());
                List<Genre> genresForThisFilm = repository.uspGetAllGenresForFilm(myfilm.getId());
                //List<Director> fdForThisFilm = repository.uspGetAllDirectorsForFilm(myfilm.getId());
                myfilm.setActors(actorsForThisFilm);
                myfilm.setGenres(genresForThisFilm);
                //myfilm.setDirectors(fdForThisFilm);
                
            }

            allFilmsCurrentlyInDatabase = films;
        } catch (Exception ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTable() throws Exception, XMLStreamException {
        Director d = new Director();

        tblFilms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblFilms.setAutoCreateRowSorter(true);
        tblFilms.setRowHeight(25);
        tableModelFilm = new TableModelFilm(allFilmsCurrentlyInDatabase);
        tblFilms.setModel(tableModelFilm);
        tblFilms.setSelectionBackground(Color.BLACK);
        tblFilms.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableModelFilm.setFilmsTableModelColumnWidthNormalView(tblFilms);

        SwingUtilities.updateComponentTreeUI(tblFilms);
    }


    private void initParsedFilms() {
        try {
            this.allParsedFilms = Parser.parseFilms();
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void uploadAllFilmsToDatabase(List<Film> allParsedFilms) {
        allParsedFilms.forEach(f -> {
            try {
                uploadNewFilm(f);

            } catch (Exception ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        initAllFilmsCurrentlyInDatabase();
        SwingUtilities.updateComponentTreeUI(this);
        
        
    }

    private void uploadNewFilm(Film f) throws Exception {
        try {
            List<Genre> genres = f.getGenres();
            List<Actor> actors = f.getActors();
            List<Director> filmDirectors = f.getDirectors();
            List<Integer> genreIds = new ArrayList<>();
            List<Integer> actorsIds = new ArrayList<>();
            List<Integer> filmDirectorsIds = new ArrayList<>();
            if (!genres.isEmpty()) {
                genres.forEach(g -> {
                    try {
                        genreIds.add(getIdGenre(g));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            if (actors != null) {
                if (!actors.isEmpty()) {
                    actors.forEach(actor -> {
                        try {
                            actorsIds.add(getIdActor(actor));
                        } catch (Exception ex) {
                            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            }

            if (!filmDirectors.isEmpty()) {
                filmDirectors.forEach(fd -> {
                    try {
                        filmDirectorsIds.add(getIdDirector(fd));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            int idFilm = repository.uspCreateFilm(f);

            genreIds.forEach((x) -> {
                try {
                    repository.uspNewGenreFilmEntry(x, idFilm);
                } catch (Exception ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            actorsIds.forEach((y) -> {
                try {
                    repository.uspNewActorFilmEntry(y, idFilm);
                } catch (Exception ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            filmDirectorsIds.forEach((z) -> {
                try {
                    repository.uspNewFDFilmEntry(z, idFilm);
                } catch (Exception ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
           
        } catch (Exception ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Integer getIdGenre(Genre g) throws Exception {
        if (false) {
            return repository.uspGetIDGenre(g);
        } else {
            return repository.uspCreateGenre(g);
        }
    }

    private Integer getIdActor(Actor actor) throws Exception {
        if (false) {
            return repository.uspGetIDActor(actor);
        } else {
            return repository.uspCreateActor(actor);
        }
    }

    private Integer getIdDirector(Director fd) throws Exception {
        if (false) {
            return repository.uspGetIDDirector(fd);
        } else {
            return repository.uspCreateNewDirector(fd);
        }
    }

    private void deletePictures() {
        File assets = new File("C:\\Users\\Bruno\\Desktop\\Java1Projekt\\Java1Projekt\\assets");
        if (assets.exists() && assets.isDirectory()) {
            File[] all = assets.listFiles();
            if (all != null) {
                for (File file : all) {
                    file.delete();
                }
            }
    }

    }
}
