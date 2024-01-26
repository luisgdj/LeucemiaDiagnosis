package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.drools.compiler.compiler.*;
import org.drools.core.*;
//import org.drools.util.*;

import entities.Patient;
import entities.Symptom;
import entities.Diagnosis;
import entities.Sex;
import services.PatientService;
import utils.ApplicationUtil;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;

public class PatientController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create() {
        Patient p = new Patient("Jaime", "Martínez", 38, Sex.MALE);
        p.setGenFiladelfia(true);
        //p.setAntecedentesFamiliares(true);
        p.setFarmacosQuimio(true);
        //p.setHermanosLeucemia(true);
        //p.setRazaBlanca(true);
        p.setRadOQuimicos(true);
        //p.setTransplanteMO(true);
        //p.setTrastornosSang(true);
        //p.setTrastornosGeneticos(true);

        List<Symptom> symptoms = new LinkedList<>();
        //symptoms.add(new Symptom("Fiebre", 4));
        symptoms.add(new Symptom("Fatiga", 6));
        symptoms.add(new Symptom("Debilidad", 14));
        //symptoms.add(new Symptom("Palidez", 13));
        //symptoms.add(new Symptom("Dificultad respiratoria", 40));
        //symptoms.add(new Symptom("Infecciones recurrentes", 50));
        symptoms.add(new Symptom("Propenso a hematomas", 65));
        //symptoms.add(new Symptom("Petequias", 58));
        //symptoms.add(new Symptom("Sudores nocturnos", 36));
        //symptoms.add(new Symptom("Sudores fríos", 40));
        //symptoms.add(new Symptom("Dolor articulaciones/huesos", 54));
        symptoms.add(new Symptom("Hepatoesplenomegalia", 72));
        //symptoms.add(new Symptom("Inflamación de ganglios linfáticos", 80));
        //symptoms.add(new Symptom("Saciedad temprana", 23));
        //symptoms.add(new Symptom("Pérdida de peso", 18));
        //symptoms.add(new Symptom("Menstruación abundante", 27));
        //symptoms.add(new Symptom("Hemorragia nasal", 32));
        //symptoms.add(new Symptom("Hemorragia/inflamación de encías", 8));

        /*
        Juan García, 62 años, Hombre
        Raza blanca, Trasplante de MO, Fármacos quimio, Antecedentes familiares
        TOTAL: (2)+2+3+2+1 = 10 -> Alta
         -Fiebre (4)
         -Infecciones recurrentes (50)
         -Sudores fríos (40)
         -Inflamación gang linf (80)
         -Pérdida de peso (18)
        TOTAL: 4+50+40+80+18 = 192
        PORCENTAJE LLC: 192/293 = 65,52901%

        María Jiménez, 8 años, Mujer
        Trastorno genético, Hermanos con leucemia, Antecedentes familiares
        TOTAL: (2)+3+3+1 = 9 -> Alta
         -Fiebre (4)
         -Debilidad (14)
         -Palidez (13)
         -Petequias (58)
         dif resp
         -Hepatoesplenomegalia (72)
         -Información gang linf (80)
         -Saciedad temprana (23)
         -Pérdida de peso (18)
         -Hemorragia nasal (32)
        TOTAL: 4+14+13+58+72+80+23+18+32 = 314
        PORCENTAJE LLA: 314/506 = 62,05534%

        Jaime Martínez, 38 años, Hombre
        Cromosoma filadelfia, Radiación o químicos, Fármacos quimio
        TOTAL: (2)+5+1+2 = 10 -> Alta
         -Fatiga (6)
         -Debilidad (14)
         -Propenso a hematomas (65)
         -Hepatoesplenomegalia (72)
        TOTAL: 6+14+65+72 = 157
        PORCENTAJE LMC: 157/255 = 61,56863%
         */

        Patient patient = PatientService.getInstance().addPatient(p);
        patient.setSymptoms(symptoms);
        JsonNode jsonObject = Json.toJson(patient);
        logger.debug("In PatientController.create(), result is: {}", jsonObject.toString());
        return created(jsonObject);
    }

    public Result createFromJSON(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In PatientController.create(), input is: {}", json.toString());
        Patient patient = PatientService.getInstance().addPatient(Json.fromJson(json, Patient.class));
        JsonNode jsonObject = Json.toJson(patient);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(int id, Http.Request request) {
        logger.debug("In PatientController.retrieve(), retrieve patient with id: {}",id);
        if (PatientService.getInstance().getPatient(id) == null) {
            return notFound(ApplicationUtil.createResponse("Patient with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(PatientService.getInstance().getPatient(id));
        logger.debug("In PatientController.update()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Patient newPatient = Json.fromJson(json, Patient.class);
        newPatient.setId(id);
        Patient patient = PatientService.getInstance().updatePatient(newPatient);
        logger.debug("In PatientController.update(), patient is: {}",patient);
        if (patient == null) {
            return notFound(ApplicationUtil.createResponse("Patient not found", false));
        }
        JsonNode jsonObject = Json.toJson(patient);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result delete(int id) {
        logger.debug("In PatientController.retrieve(), delete patient with id: {}",id);
        if (!PatientService.getInstance().deletePatient(id)) {
            return notFound(ApplicationUtil.createResponse("Patient with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Patient with id:" + id + " deleted", true));
    }

    public Result listPatients() {
        Set<Patient> result = PatientService.getInstance().getAllPatients();
        logger.debug("In PatientController.listPatients(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    public Result retrieve(int id) {
        logger.debug("In PatientController.retrieve(), retrieve patient with id: {}",id);
        if (PatientService.getInstance().getPatient(id) == null) {
            return notFound(ApplicationUtil.createResponse("Patient with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(PatientService.getInstance().getPatient(id));
        logger.debug("In PatientController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result generalDiagnosis(int id) {
        logger.debug("In PatientController.retrieve(), retrieve patient with id: {}", id);
        if (PatientService.getInstance().getPatient(id) == null) {
            return notFound(ApplicationUtil.createResponse("Patient with id: " + id + " not found", false));
        }
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("leucemiaKS");

        Patient patient = PatientService.getInstance().getPatient(id);
        ksession.insert(patient);

        List<Diagnosis> diagnosis = patient.getDiagnosis();
        for(Diagnosis d: diagnosis) {
            ksession.insert(d);
        }

        List<Symptom> symptoms = patient.getSymptoms();
        for(Symptom s: symptoms) {
            ksession.insert(s);
        }

        ksession.fireAllRules();
        ksession.dispose();

        JsonNode jsonObjects = Json.toJson(diagnosis);
        logger.debug("In PatientController.generalDiagnosis(), result is: {}", jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }
}