package Client.repository;

import Client.model.ClientBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientBlacklistRespository extends JpaRepository<ClientBlacklist,Long> {
}
