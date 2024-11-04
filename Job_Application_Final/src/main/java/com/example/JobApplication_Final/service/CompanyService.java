package com.example.JobApplication_Final.service;

import com.example.JobApplication_Final.model.Company;
import com.example.JobApplication_Final.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public boolean deleteCompanyById(long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCompany(long id, Company company) {
        Optional<Company> oldCompany = companyRepository.findById(id);

        if(oldCompany.isPresent()) {
            Company existingCompany = oldCompany.get();

            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getName());
            existingCompany.setJobs(company.getJobs());

            companyRepository.save(existingCompany);
            return true;
        }
        return false;
    }
}
