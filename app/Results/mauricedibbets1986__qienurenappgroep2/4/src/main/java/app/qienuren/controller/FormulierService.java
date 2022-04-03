package app.qienuren.controller;
 import app.qienuren.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;
import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import app.qienuren.Interface.MedewerkerRepository;
import app.qienuren.Interface.EmailService;
import app.qienuren.DTO.Medewerker;
@Service
@Transactional
public class FormulierService {

@Autowired
 private FormulierRepository formulierRepository;

@Autowired
 private WerkDagRepository werkDagRepository;

@Autowired
 private MedewerkerRepository medewerkerRepository;

@Autowired
 private EmailService emailService;


public Formulier addNieuwFormulier(Formulier formulier){
    System.out.println("formulier aangemaakt");
    return formulierRepository.save(formulier);
}


public Formulier AdminStatusGoed(long formulierid,long medewerkerid){
    System.out.println("hij doet updaten");
    Formulier formuliertijdelijk = formulierRepository.findById(formulierid).get();
    formuliertijdelijk.setAdminStatus(AdminStatus.GOEDGEKEURD);
    // Zet tijdelijkformulier naar false
    formuliertijdelijk.setTijdelijkFormulier(false);
    // Voeg toe aan archief en verwijder uit lijst tijdelijkeformulieren
    Medewerker m = medewerkerRepository.findById(medewerkerid).get();
    m.voegFormulierToeAanArchief(formuliertijdelijk);
    m.verwijderFormulierUitTijdelijkeLijst(formuliertijdelijk);
    // Send email
    emailService.sendWithFormulierBeoordelingTemplate(m, formuliertijdelijk.getAdminStatus());
    medewerkerRepository.save(m);
    return formulierRepository.save(formuliertijdelijk);
}


public Iterable<Formulier> getAlleFormulierenVoorOpdrachtGever(){
    Iterable<Formulier> formulieren = formulierRepository.findAll();
    ArrayList<Formulier> teVerzendenFormulieren = new ArrayList<>();
    for (Formulier f : formulieren) {
        if (f.isIngezondenFormulier() == true && f.isTijdelijkFormulier() == true) {
            teVerzendenFormulieren.add(f);
        }
    }
    return teVerzendenFormulieren;
}


public Formulier AdminStatusFout(long id,long medewerkerid){
    System.out.println("hij doet fout updaten");
    Formulier formuliertijdelijk = formulierRepository.findById(id).get();
    Medewerker m = medewerkerRepository.findById(medewerkerid).get();
    formuliertijdelijk.setAdminStatus(AdminStatus.AFGEKEURD);
    formuliertijdelijk.setIngezondenFormulier(false);
    // Send email
    emailService.sendWithFormulierBeoordelingTemplate(m, formuliertijdelijk.getAdminStatus());
    return formulierRepository.save(formuliertijdelijk);
}


public Formulier getById(long id){
    return formulierRepository.findById(id).get();
}


public Formulier OpdrachtgeverStatusGoed(long id,long medewerkerid){
    System.out.println("hij doet updaten");
    Formulier formuliertijdelijk = formulierRepository.findById(id).get();
    formuliertijdelijk.setOpdrachtgeverStatus(OpdrachtgeverStatus.GOEDGEKEURD);
    // Send email
    // Arguments: Medewerker, Formulier, Subject, Message(templated?)
    // Medewerker fields nodig: Name
    // Formulier fields nodig: Maand/Jaar?, Beoordeling
    Medewerker m = medewerkerRepository.findById(medewerkerid).get();
    emailService.sendWithFormulierBeoordelingTemplate(m, formuliertijdelijk.getOpdrachtgeverStatus());
    System.out.println("In de OpdrachtgeverGoed status methode");
    return formulierRepository.save(formuliertijdelijk);
}


public Formulier OpdrachtgeverStatusFout(long id,long medewerkerid){
    System.out.println("hij doet fout updaten");
    Formulier formuliertijdelijk = formulierRepository.findById(id).get();
    formuliertijdelijk.setOpdrachtgeverStatus(OpdrachtgeverStatus.AFGEKEURD);
    formuliertijdelijk.setIngezondenFormulier(false);
    // Send email
    // Arguments: Medewerker, Formulier, Subject, Message(templated?)
    // Medewerker fields nodig: Name
    // Formulier fields nodig: Maand/Jaar?, Beoordeling
    Medewerker m = medewerkerRepository.findById(medewerkerid).get();
    emailService.sendWithFormulierBeoordelingTemplate(m, formuliertijdelijk.getOpdrachtgeverStatus());
    return formulierRepository.save(formuliertijdelijk);
}


public Formulier updateFormulier(Formulier nieuwF){
    // oude formulier ophalen
    Formulier oudF = formulierRepository.findById(nieuwF.getId()).get();
    List<WerkDag> nieuweWerkDagen = nieuwF.getWerkDagen();
    List<WerkDag> oudeWerkDagen = oudF.getWerkDagen();
    for (int i = 0; i < nieuweWerkDagen.size(); i++) {
        if (nieuweWerkDagen.get(i).getOpdrachtUren() != 0) {
            oudeWerkDagen.get(i).setOpdrachtUren(nieuweWerkDagen.get(i).getOpdrachtUren());
        }
        if (nieuweWerkDagen.get(i).getOverwerkUren() != 0) {
            oudeWerkDagen.get(i).setOverwerkUren(nieuweWerkDagen.get(i).getOverwerkUren());
        }
        if (nieuweWerkDagen.get(i).getVerlofUren() != 0) {
            oudeWerkDagen.get(i).setVerlofUren(nieuweWerkDagen.get(i).getVerlofUren());
        }
        if (nieuweWerkDagen.get(i).getZiekteUren() != 0) {
            oudeWerkDagen.get(i).setZiekteUren(nieuweWerkDagen.get(i).getZiekteUren());
        }
        if (nieuweWerkDagen.get(i).getTrainingsUren() != 0) {
            oudeWerkDagen.get(i).setTrainingsUren(nieuweWerkDagen.get(i).getTrainingsUren());
        }
        if (nieuweWerkDagen.get(i).getOverigeUren() != 0) {
            oudeWerkDagen.get(i).setOverigeUren(nieuweWerkDagen.get(i).getOverigeUren());
        }
        if (nieuweWerkDagen.get(i).getOverigeUrenUitleg() != "" || !(nieuweWerkDagen.get(i).getOverigeUrenUitleg().isEmpty())) {
            oudeWerkDagen.get(i).setOverigeUrenUitleg(nieuweWerkDagen.get(i).getOverigeUrenUitleg());
        }
    }
    oudF.setIngezondenFormulier(nieuwF.isIngezondenFormulier());
    oudF.setOpdrachtgeverStatus(nieuwF.getOpdrachtgeverStatus());
    oudF.setAdminStatus(nieuwF.getAdminStatus());
    System.out.println("Oud TF: " + oudF.getWerkDagen().get(0).getOpdrachtUren());
    System.out.println("nieuw TF: " + nieuwF.getWerkDagen().get(0).getOpdrachtUren());
    System.out.println("Oud F:" + oudF.isIngezondenFormulier());
    System.out.println("nieuw F: " + nieuwF.isIngezondenFormulier());
    return formulierRepository.save(oudF);
}


public void verwijderFormulier(long id){
    System.out.println("Het formulier is verwijderd");
    formulierRepository.deleteById(id);
}


public Iterable<Formulier> getalleFormulieren(){
    return formulierRepository.findAll();
}


public Iterable<Formulier> getAlleFormulierenVoorAdmin(){
    Iterable<Formulier> formulieren = formulierRepository.findAll();
    ArrayList<Formulier> teVerzendenFormulieren = new ArrayList<>();
    for (Formulier f : formulieren) {
        if (f.isIngezondenFormulier() == true && f.isTijdelijkFormulier() == true) {
            teVerzendenFormulieren.add(f);
        }
    }
    return teVerzendenFormulieren;
}


public List<String[]> exportCSV(Formulier formulierExport,Persoon persoonExport) throws IOException{
    // CSVWriter writer = new CSVWriter(new FileWriter("Urenformulier-" + persoonExport.getNaam() + "-" + formulierExport.getMaand() + "-" + formulierExport.getJaar() +   ".csv"), ';', '"', '\\', CSVWriter.DEFAULT_LINE_END);
    List<String[]> rijenCSV = new ArrayList<>();
    // checken of de persoon een Trainee of Interne Medewerker is
    String bedrijf = "";
    if ((persoonExport.getRoles()).equals("ROLE_INTERNEMEDEWERKER")) {
        bedrijf = "Qien B.V.";
    } else if ((persoonExport.getRoles()).equals("ROLE_TRAINEE")) {
        Trainee exportTrainee = (Trainee) persoonExport;
        try {
            bedrijf = exportTrainee.getLeidingGevende().getBedrijf().getNaam();
            System.out.println("CSVExport ging goed in the service");
        } catch (NullPointerException e) {
            bedrijf = "Niet gekoppeld";
        }
    } else {
        bedrijf = "Dit is niet helemaal goed gegaan";
    }
    // basisgegevens formulier, medewerker en opdrachtgever ophalen en in array laden
    String[] FormulierID = new String[] { "id : ", String.valueOf(formulierExport.getId()) };
    String[] MedewerkerNaam = new String[] { "naam : ", persoonExport.getNaam() };
    String[] OpdrachtGever = new String[] { "opdrachtgever : ", bedrijf };
    String[] FormulierMaand = new String[] { "maand : ", String.valueOf(formulierExport.getMaand()) };
    String[] FormulierJaar = new String[] { "jaar : ", String.valueOf(formulierExport.getJaar()) };
    rijenCSV.add(FormulierID);
    rijenCSV.add(MedewerkerNaam);
    rijenCSV.add(OpdrachtGever);
    rijenCSV.add(FormulierMaand);
    rijenCSV.add(FormulierJaar);
    // rest array vullen met de gemaakte uren formulier werkdagen
    // header formuleren
    String[] header = new String[] { "datum", "opdracht", "overwerk", "verlof", "ziek", "training", "overig", "verklaring m.b.t. tot overig" };
    rijenCSV.add(header);
    // werkdagen uit formulier halen
    List<WerkDag> werkDagenExport = formulierExport.getWerkDagen();
    DateTimeFormatter datumExport = DateTimeFormatter.ofPattern("d");
    for (WerkDag dagExport : werkDagenExport) {
        String[] rijToevoegen = new String[] { datumExport.format(dagExport.getDatum()), String.valueOf(dagExport.getOpdrachtUren()), String.valueOf(dagExport.getOverwerkUren()), String.valueOf(dagExport.getVerlofUren()), String.valueOf(dagExport.getZiekteUren()), String.valueOf(dagExport.getTrainingsUren()), String.valueOf(dagExport.getOverigeUren()), dagExport.getOverigeUrenUitleg() };
        rijenCSV.add(rijToevoegen);
    }
    return rijenCSV;
}


}