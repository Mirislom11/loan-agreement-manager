package uz.com.tuir.loanagreementmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.com.tuir.loanagreementmanager.entity.LoanAgreementEntity;

@Repository
public interface LoanAgreementRepo extends JpaRepository<LoanAgreementEntity, Long> {
}
