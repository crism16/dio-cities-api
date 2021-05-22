package com.github.cristianesilva.cites.api.countries.repository;

import com.github.cristianesilva.cites.api.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
