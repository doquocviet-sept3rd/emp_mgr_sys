﻿# emp_mgr_sys

#User manual

#important: clean maven and install maven 

[1] Create a database and tables with code first.
- Go to mysql, create a new database with name is emp_mgr_sys, or you can change the name arbitrarily in the file hibernate.cfg.xml.
- Go to project, resource/META-INF/hibernate.cfg and change username and password of your mysql.
- Next, run class initScheme in src/main/java/system/utils/initScheme.java. Then, system will automatically create tables in your mysql.
- In the project will appear a script.sql file, this file is a render file from the code.

[2] Init data in mysql.
- Go to file data.sql in the project, copy all and paste in mysql, mysql will automatically generate data.

[3] Run project and use. (username: admin, password: 123456)

#Technology used
- JPA - Hibernate framework.
- Database system: Mysql, database management system: Mysql workbench 8.1.
- Java swing
- Tool: JFormDesigner

#Information 
#Hibernate framework
Hibernate ORM (or simply Hibernate) is an object–relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database. Hibernate handles object–relational impedance mismatch problems by replacing direct, persistent database accesses with high-level object handling functions.

Hibernate is free software that is distributed under the GNU Lesser General Public License 2.1.

Hibernate's primary feature is mapping from Java classes to database tables, and mapping from Java data types to SQL data types. Hibernate also provides data query and retrieval facilities. It generates SQL calls and relieves the developer from the manual handling and object conversion of the result set.

Mapping
The mapping of Java classes to database tables is implemented by the configuration of an XML file or by using Java Annotations. When using an XML file, Hibernate can generate skeleton source code for the persistence classes. This is auxiliary when annotations are used. Hibernate can use the XML file or the Java annotations to maintain the database schema.

There are provided facilities to arrange one-to-many and many-to-many relationships between classes. In addition to managing associations between objects, Hibernate can also manage reflexive associations wherein an object has a one-to-many relationship with other instances of the class type.

Hibernate supports the mapping of custom value types. This makes the following scenarios possible:

Overriding the default SQL type when mapping a column to a property.
Mapping Java Enums to columns as though they were regular properties.
Mapping a single property to multiple columns.
Definition: Objects in an object-oriented application follow OOP principles, while objects in the back-end follow database normalization principles, resulting in different representation requirements. This problem is called "object–relational impedance mismatch". Mapping is a way of resolving the object–relational impedance mismatch problem.

Mapping informs the ORM tool of what Java class object to store in which database table.

Hibernate Query Language (HQL)
Hibernate provides a SQL inspired language called Hibernate Query Language (HQL) for writing SQL-like queries against Hibernate's data objects. Criteria Queries are provided as an object-oriented alternative to HQL. Criteria Query is used to modify the objects and provide the restriction for the objects. HQL (Hibernate Query Language) is the object-oriented version of SQL. It generates database independent queries so that there is no need to write database-specific queries. Without this capability, changing the database would require individual SQL queries to be changed as well, leading to maintenance issues.

Persistence
Hibernate provides transparent persistence for Plain Old Java Objects (POJOs). The only strict requirement for a persistent class is a no-argument constructor, though not necessarily public. Proper behavior in some applications also requires special attention to the equals() and hashCode() methods in the object classes.[3] Hibernate recommends providing an identifier attribute, and this is planned to be a mandatory requirement in a future release.

Collections of data objects are typically stored in Java collection classes, such as implementations of the Set and List interfaces. Java generics, introduced in Java 5, are also supported. Hibernate can be configured to lazy load associated collections. Lazy loading is the default as of Hibernate 3.

Related objects can be configured to cascade operations from one object to the other. For example, a parent Album class object can be configured to cascade its save and delete operations to its child Track class objects.

Integration
Hibernate can be used both in standalone Java applications and in Java EE applications using servlets, EJB session beans, and JBI service components. It can also be included as a feature in other programming languages. For example, Adobe integrated Hibernate into version 9 of ColdFusion (which runs on J2EE app servers) with an abstraction layer of new functions and syntax added into CFML.

Entities and components
In Hibernate jargon, an entity is a stand-alone object in Hibernate's persistent mechanism which can be manipulated independently of other objects. In contrast, a component is subordinate to an entity and can be manipulated only with respect to that entity. For example, an Album object may represent an entity; but the Tracks object associated with the Album objects would represent a component of the Album entity, if it is assumed that Tracks can only be saved or retrieved from the database through the Album object. Unlike J2EE, Hibernate can switch databases.

History
Hibernate was started in 2001 by Gavin King with colleagues from Cirrus Technologies as an alternative to using EJB2-style entity beans. The original goal was to offer better persistence capabilities than those offered by EJB2; by simplifying the complexities and supplementing certain missing features.

In early 2003, the Hibernate development team began Hibernate2 releases, which offered many significant improvements over the first release.

JBoss, Inc. (now part of Red Hat) later hired the lead Hibernate developers in order to further its development.

In 2005, Hibernate version 3.0 was released. Key features included a new Interceptor/Callback architecture, user defined filters, and JDK 5.0 Annotations (Java's metadata feature). As of 2010, Hibernate 3 (version 3.5.0 and up) was a certified implementation of the Java Persistence API 2.0 specification via a wrapper for the Core module which provides conformity with the JSR 317 standard.

In Dec 2011, Hibernate Core 4.0.0 Final was released. This includes new features such as multi-tenancy support, introduction of ServiceRegistry (a major change in how Hibernate builds and manages "services"), better session opening from SessionFactory, improved integration via org.hibernate.integrator.spi.Integrator and auto discovery, internationalization support, message codes in logging, and a more distinction between the API, SPI or implementation classes.

In December 2012, Hibernate ORM 4.1.9 Final was released.

In Mar 2013, Hibernate ORM 4.2 Final was released.

In December 2013, Hibernate ORM 4.3.0 Final was released. It features Java Persistence API 2.1.

In September 2015, Hibernate ORM 5.0.2 Final was released. It has improved bootstrapping, hibernate-java8, hibernate-spatial, Karaf support.

In November 2018, Hibernate ORM 5.1.17 Final was released. This is the final release of the 5.1 series.

In October 2018, Hibernate ORM 5.3 Final was released. It featured Java Persistence API 2.2 inheritance caching.

In December 2018, Hibernate ORM 5.4.0 Final was released.

Application programming interface
The Hibernate API is provided in the Java package org.hibernate.

org.hibernate.SessionFactory interface
org.hibernate.Session interface
The org.hibernate.Session interface represents a Hibernate session, i.e., the main point of the manipulation performed on the database entities. The latter activities include (among the other things) managing the persistence state (transient, persisted, detached[clarification needed]) of the objects, fetching the persisted ones from the database and the management of the transaction demarcation[clarification needed].

A session is intended to last as long as the logical transaction on the database. Due to the latter feature, Session implementations are not expected to be thread safe nor to be used by multiple clients.

Software components
The Hibernate software includes the following components:

Hibernate ORM (known as Hibernate Core before release 4.1) – the base software for an object–relational mapping solution for Java environments.
Hibernate Annotations (merged into Hibernate Core/ORM since version 3.6 – metadata that governs the transformation of data between the object-oriented model and the relational database model according to the JSR 317 Java Persistence API (JPA 2).
Hibernate EntityManager (merged into Hibernate Core/ORM since version 5.2 – together with Hibernate Annotations, a wrapper that implements a JSR 317 Java Persistence API (JPA 2) persistence solution.
Hibernate Envers – auditing and versioning of persistent classes.
Hibernate OGM (Object/Grid Mapper) – an extension to store data in a NoSQL store.
Hibernate Shards – horizontal partitioning for multiple relational databases.
While Hibernate Shards is not compatible with 4.x releases of Hibernate Core, some of the Shards capability was integrated into Core in the 4.0 release
Hibernate Search – integrates the full text library functionality from Apache Lucene in the Hibernate and JPA model.
Hibernate Tools – a set of tools implemented as a suite of Eclipse plugins and Ant tasks included in JBoss Developer Studio.
Hibernate Validator – the reference implementation of JSR 303 Bean Validation.
Hibernate Metamodel Generator – an annotation processor that creates JSR 317 Java Persistence API (JPA 2) static metamodel classes using the JSR 269 Pluggable Annotation Processing API.
NHibernate – an object–relational mapping solution for the .NET Framework.

#JFormDesigner
JFormDesigner™ is a professional GUI designer for Java™ Swing user interfaces. Its outstanding support for MigLayout, JGoodies FormLayout, GroupLayout (Free Design), TableLayout and GridBagLayout makes it easy to create professional looking forms.

#Java Swing
Java Swing tutorial is a part of Java Foundation Classes (JFC) that is used to create window-based applications. It is built on the top of AWT (Abstract Windowing Toolkit) API and entirely written in java.

Unlike AWT, Java Swing provides platform-independent and lightweight components.

The javax. Swing package provides classes for java swing API such as JButton, JTextField, JTextArea, JRadioButton, JCheckbox, JMenu, JColorChooser etc.

#Mysql
MySQL is an open-source relational database management system (RDBMS). Its name is a combination of "My", the name of co-founder Michael Widenius's daughter, and "SQL", the abbreviation for Structured Query Language. A relational database organizes data into one or more data tables in which data types may be related to each other; these relations help structure the data. SQL is a language programmers use to create, modify and extract data from the relational database, as well as control user access to the database. In addition to relational databases and SQL, an RDBMS like MySQL works with an operating system to implement a relational database in a computer's storage system, manages users, allows for network access and facilitates testing database integrity and creation of backups.

MySQL is free and open-source software under the terms of the GNU General Public License, and is also available under a variety of proprietary licenses. MySQL was owned and sponsored by the Swedish company MySQL AB, which was bought by Sun Microsystems (now Oracle Corporation). In 2010, when Oracle acquired Sun, Widenius forked the open-source MySQL project to create MariaDB.

MySQL has stand-alone clients that allow users to interact directly with a MySQL database using SQL, but more often, MySQL is used with other programs to implement applications that need relational database capability. MySQL is a component of the LAMP web application software stack (and others), which is an acronym for Linux, Apache, MySQL, Perl/PHP/Python. MySQL is used by many database-driven web applications, including Drupal, Joomla, phpBB, and WordPress. MySQL is also used by many popular websites, including Facebook, Flickr, MediaWiki, Twitter, and YouTube.
