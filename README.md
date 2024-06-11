# Insurance Subscription System Using Java and Camunda

![Project Logo](https://github.com/SBJ2000/Insurance-Subscription-System-Using-Java-and-Camunda/blob/main/Images/Logo.jpg)

## Description:
This project implements an insurance subscription system in Tunisia using a microservices architecture and Camunda for workflow management. The system covers three main phases: simulation and request, scoring, and decision-making. Each phase is handled by a dedicated microservice, and the entire process is orchestrated using Camunda BPM.

## Objectives

* Simulation and Request: Capture client information, provide suitable insurance offers based on a product catalog.
* Scoring: Calculate and save a score for each client’s request based on their risk profile.
* Decision: Automatically decide whether to accept or reject an insurance request based on the score.

# Work Environment
Programming Language: 

[![Java](https://img.shields.io/badge/Java-17.0.2-blue)](https://www.oracle.com/java/)

Frameworks:

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.6.2-brightgreen)](https://spring.io/projects/spring-data-jpa)

Database:

[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue)](https://www.postgresql.org/)

Workflow Engine:

[![Camunda BPM](https://img.shields.io/badge/Camunda%20BPM-7.15.0-red)](https://camunda.com/)

Build Tool:

[![Maven](https://img.shields.io/badge/Maven-3.8.4-orange)](https://maven.apache.org/)

Version Control:

[![Git](https://img.shields.io/badge/Git-2.34.1-lightgrey)](https://git-scm.com/)


## Architecture:

The system consists of five microservices, each responsible for a distinct part of the insurance subscription process:

### Microservice: Client Management
APIs:

* GET /Client - Retrieve all clients.
* GET /Client/{id} - Retrieve a client by ID.
* POST /Client/add-client/{nom}/{prenom}/{cin}/{revenuMensuel}/{typeEmploi}/{classeBonusMalus}/{besoinAssurance} - Add a new client.
* GET /Client/prenom/{id} - Retrieve a client's first name by ID.
* PUT /Client/{id} - Update a client’s information by ID.
* DELETE /Client/{id} - Delete a client by ID.
* GET /Client/besoin-assurance/{id} - Retrieve a client's insurance need by ID.
* POST /Client/add-to-blacklist/{clientId} - Add a client to the blacklist by ID.
* GET /Client/{id}/revenu-mensuel - Retrieve a client's monthly income by ID.
* GET /Client/classe-bonus-malus/{clientID} - Retrieve a client's bonus-malus class by ID.

### Microservice: Blacklisted Client Management
APIs:

* GET /client-blacklist - Retrieve all blacklisted clients.
* GET /client-blacklist/{clientID} - Check if a client is blacklisted by ID.
* GET /client-blacklist/exists-by-prenom/{prenom} - Check if a client exists in the blacklist by first name.
* POST /client-blacklist/add-client-to-blacklist/{idClient} - Add a client to the blacklist by ID.
* PUT /client-blacklist/{clientID} - Update a blacklisted client’s information by ID.
* DELETE /client-blacklist/{clientID} - Delete a blacklisted client by ID.

### Microservice: Insurance Offer Management
APIs:

* GET /offres - Retrieve all insurance offers.
* GET /offres/{id} - Retrieve an insurance offer by ID.
* POST /offres/add-offre/{referenceProduit}/{typeAssurance}/{plafondGarantie}/{primeAssurance}/{conditionsParticulieres} - Add a new insurance offer.
* PUT /offres/{id} - Update an insurance offer by ID.
* DELETE /offres/{id} - Delete an insurance offer by ID.
* GET /offres/find-offre-by-amount-and-type/{amount}/{typeAssurance} - Retrieve an insurance offer by amount and type.

### Microservice: Insurance Request Management
APIs:

* GET /demande - Retrieve all insurance requests.
* GET /demande/{id} - Retrieve an insurance request by ID.
* GET /demande/client-id/{id} - Retrieve the client ID associated with a request by request ID.
* POST /demande/{idClient} - Add a new insurance request for a specified client.
* PUT /demande/{id} - Update an insurance request by ID.
* DELETE /demande/{id} - Delete an insurance request by ID.

### Microservice: Scoring Management
APIs:

* GET /scoring - Retrieve all scores.
* GET /scoring/{scoringID} - Retrieve a score by ID.
* POST /scoring/add-scoring/{idDemande} - Add a new score for a specified request.
* GET /scoring/etatscore/{idDemande} - Retrieve the score state by request ID.
* PUT /scoring/{scoringID} - Update a score by ID.
* DELETE /scoring/{scoringID} - Delete a score by ID.

### Microservice: Decision Management
APIs:

* GET /decision - Retrieve all decisions.
* GET /decision/{idDecision} - Retrieve a decision by ID.
* POST /decision/{idDemande} - Add a new decision for a specified request.
* PUT /decision/{idDecision} - Update a decision by ID.
* DELETE /decision/{idDecision} - Delete a decision by ID.

![Architecture](https://github.com/SBJ2000/Insurance-Subscription-System-Using-Java-and-Camunda/blob/main/Images/Architecture.png)

## Camunda Workflow :

The Camunda BPMN workflow manages the entire insurance subscription process, including:

* Client information retrieval and validation
* Blacklist check
* Score calculation based on client data and insurance needs
* Decision-making based on the score
* Storing the final decision

The workflow uses service tasks to call the microservice APIs and manage the data flow between different stages of the process.

![Camunda](https://github.com/SBJ2000/Insurance-Subscription-System-Using-Java-and-Camunda/blob/main/Images/Camunda.png)

## Installation :

1- Clone the repository:
        
    git clone https://github.com/yourusername/insurance-subscription-system.git

2- Navigate to each microservice directory and build with Maven:

    cd microservice-directory
    mvn clean install

3- Configure the PostgreSQL database for each microservice:
Update the application.properties file with your database credentials.

4- Run each microservice:

    mvn spring-boot:run

5- Deploy the Camunda BPMN workflow:

* Import the BPMN file into the Camunda Modeler. 
* Deploy the workflow to your Camunda engine.

## Usage

1- Client Management:

* Add clients using the provided APIs.

* Retrieve and manage client information.

2- Insurance Offer Management:

* Add and retrieve insurance offers.

3- Insurance Request Management:

* Create and manage insurance requests for clients.

4- Scoring:

* Calculate scores for insurance requests.

5- Decision Making:

* Automatically make decisions based on the calculated scores.

## Conclusion :

This project demonstrates a comprehensive approach to building a microservices-based insurance subscription system integrated with Camunda for workflow management. It covers client management, scoring, and automated decision-making processes. The system is designed to be scalable, maintainable, and easily extensible for future enhancements.