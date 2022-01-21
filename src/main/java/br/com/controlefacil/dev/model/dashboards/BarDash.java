package br.com.controlefacil.dev.model.dashboards;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;


public interface BarDash {

     String getMonths();
     Long getCount();
}
