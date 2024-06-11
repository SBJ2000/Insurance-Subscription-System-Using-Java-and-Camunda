package DemandeAssurance.service;

import DemandeAssurance.model.DemandeAssurance;
import DemandeAssurance.repository.DemandeAssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeAssuranceService {

    @Autowired
    private DemandeAssuranceRepository demandeAssuranceRepository;

    public List<DemandeAssurance> getAllDemandes() {
        return demandeAssuranceRepository.findAll();
    }

    public DemandeAssurance getDemandeById(Long id) {
        return demandeAssuranceRepository.findById(id).orElse(null);
    }

    public Long addDemande(DemandeAssurance demandeAssurance) {
        demandeAssuranceRepository.save(demandeAssurance);
        return demandeAssurance.getDemandeID();
    }
    public Long getClientIdByDemandeId(Long id) {
        Optional<DemandeAssurance> optionalDemande = demandeAssuranceRepository.findById(id);

        if (optionalDemande.isPresent()) {
            return optionalDemande.get().getIdClient();
        } else {
            return null;
        }
    }
    public DemandeAssurance updateDemande(Long id, DemandeAssurance demandeAssurance) {
        if (demandeAssuranceRepository.existsById(id)) {
            demandeAssurance.setDemandeID(id);
            return demandeAssuranceRepository.save(demandeAssurance);
        }
        return null;
    }

    public void deleteDemande(Long id) {
        demandeAssuranceRepository.deleteById(id);
    }
}
