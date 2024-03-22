package com.base.api.util;


import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 
* Clase Base para identificar un <Strong>DB modelo</Strong>  en el sistema. 
*
* @author AAlejo
*/

@Entity
@Table(name = "entity_name")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelBase {


		/**
		 * Variable de identificaci�n �nica generada por sequencia de base de datos.
		 */
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, 
								   generator="entity_id_seq")
		@SequenceGenerator(name = "entity_id_seq", 
						   sequenceName="entity_id_seq", 
						   allocationSize = 1)
		@Column(name = "id")
		private Integer id;
		
		/**
		 * var1
		 */
		@Column(name = "var1")
		private String var1;
		
		/**
		 * Variable que identifica la clave con la que el usuario inicia sesion en la app.
		 */
		@Column(name = "var2")
		private  String var2;
		
		/**
		 * Variable que identifica la fecha de creacion del usuario.
		 */
		@Column(name = "var3")
		private  String var3;
		
		/**
		 * Variable que identifica la ultima conexion al sistema
		 */
		@Column(name = "last_entry_date")
		private  Date lastEntryDate;
		
		
		//RELACIONES MUCHOS A MUCHOS ENTIDAD LOCAL CONTRA TABLA PIBOT
//		/**
//		 * Variable que permite relacionar un var1 con una o var5 
//		 */
//		@ManyToMany(fetch = FetchType.EAGER)
//		@JoinTable(name="table_pibot",
//		joinColumns=@JoinColumn(name="id_var1"),
//		inverseJoinColumns=@JoinColumn(name="id_var5"))
//		private Set<Var5> relationInPlural = new HashSet<Var5>();
//		
		
}
