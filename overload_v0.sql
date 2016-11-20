--- ######################################################
--- OVERLOAD database build script.
---   - for the indial creation of the database.
---   - Refer updates for each applcaiotn release.
--- ######################################################

DROP SCHEMA IF EXISTS "overload" CASCADE;

CREATE SCHEMA "overload" AUTHORIZATION postgres;

--- COMMIT;

--- BASE OBJECTS

-- Table: overload.contact

CREATE TABLE overload.contact
(
  contact_id serial NOT NULL,
  firstname character(64),
  middlename character(64),
  surname character(64),
  extra hstore,
  email_address character(128),
  phone hstore,
  CONSTRAINT contact_pk PRIMARY KEY (contact_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.contact OWNER TO postgres;
COMMENT ON TABLE overload.contact IS 'Holds the contact details for a person';
COMMENT ON COLUMN overload.contact.extra IS 'Holds the extra info realting to the person. This includes info used in EOI checks (licence# and DOB)';

CREATE TABLE overload.address
(
  address_id serial NOT NULL,
  address_line_1 character varying(100),
  address_line_2 character varying(100),
  town character varying(50),
  state_id integer,
  country_id integer,
  postcode character varying(4),
  the_geom geometry(Geometry,4326),
  CONSTRAINT addres_px PRIMARY KEY (address_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.address OWNER TO postgres;
COMMENT ON COLUMN overload.address.the_geom IS 'Point location of the address.';

-- Table: overload.organisation

CREATE TABLE overload.organisation
(
  organisation_id serial NOT NULL,
  organisation_name character varying(10) NOT NULL,
  organisation_type integer NOT NULL,
  contact_id integer,
  address_id integer,
  extra hstore,
  CONSTRAINT organisation_pk PRIMARY KEY (organisation_id),
  CONSTRAINT organisation_fk1 FOREIGN KEY (contact_id)
      REFERENCES overload.contact (contact_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT organisation_fk2 FOREIGN KEY (address_id)
      REFERENCES overload.address (address_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.organisation OWNER TO postgres;
COMMENT ON TABLE overload.organisation IS 'Holds both internal organisations and external customer (operators).  Should only update customers via the application modification.'; 

--- USERs

-- Table: overload."user"

CREATE TABLE overload."user"
(
  user_id serial NOT NULL,
  user_name character(128) NOT NULL,
  contact_id integer,
  organisation_id integer,
  password_salt character(10),
  password character(32),
  locked boolean,
  CONSTRAINT user_pk PRIMARY KEY (user_id),
  CONSTRAINT user_fk1 FOREIGN KEY (contact_id)
      REFERENCES overload.contact (contact_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_fk2 FOREIGN KEY (organisation_id)
      REFERENCES overload.organisation (organisation_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload."user" OWNER TO postgres;

-- Table: overload.role

CREATE TABLE overload.role
(
  role_id serial NOT NULL,
  authorisation character varying(255),
  role_function character(64),
  CONSTRAINT role_pk PRIMARY KEY (role_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.role OWNER TO postgres;

-- Table: overload.user_role

CREATE TABLE overload.user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_px PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_role_fk1 FOREIGN KEY (user_id)
      REFERENCES overload."user" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_role_fk2 FOREIGN KEY (role_id)
      REFERENCES overload.role (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.user_role OWNER TO postgres;


--- GIS Information

-- Table: overload.area_of_operation

CREATE TABLE overload.area_of_operation
(
  area_of_operation_id serial NOT NULL,
  name text,
  descritpion text,
  the_geom geometry(Geometry,4326),
  CONSTRAINT area_of_opeartion_px PRIMARY KEY (area_of_operation_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.area_of_operation OWNER TO postgres;

COMMENT ON COLUMN overload.area_of_operation.name IS 'Tag of the area';
COMMENT ON COLUMN overload.area_of_operation.descritpion IS 'Extra description of the area';
COMMENT ON COLUMN overload.area_of_operation.the_geom IS 'Polygon (usually circle) to enclose the area of operation';

-- Table: overload.route

CREATE TABLE overload.route
(
  route_id serial NOT NULL,
  the_geom geometry(Geometry,4326),
  directions character varying(255),
  CONSTRAINT route_px PRIMARY KEY (route_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.route   OWNER TO postgres;

COMMENT ON TABLE overload.route
  IS 'The geometry and travel directions of a route.';
COMMENT ON COLUMN overload.route.the_geom IS 'Geometry of route (usually a line-string)';
COMMENT ON COLUMN overload.route.directions IS 'Travel directions for the route';

-- Table: overload.route_address

CREATE TABLE overload.route_address
(
  route_address_id serial NOT NULL,
  route_id integer NOT NULL,
  sequence_number integer NOT NULL,
  address_id integer NOT NULL,
  CONSTRAINT route_address_px PRIMARY KEY (route_address_id),
  CONSTRAINT route_address_fk1 FOREIGN KEY (route_id)
      REFERENCES overload.route (route_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT route_address_fk2 FOREIGN KEY (address_id)
      REFERENCES overload.address (address_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.route_address OWNER TO postgres;

COMMENT ON COLUMN overload.route_address.address_id IS 'Each address the route will stop at (incl origin)';

CREATE INDEX route_address_idx1
  ON overload.route_address
  USING btree
  (route_id, sequence_number, address_id);


-- Table: overload.saved_route

CREATE TABLE overload.saved_route
(
  saved_route_id serial NOT NULL,
  shortname character varying(20),
  description character varying(100),
  organisation_id integer,
  route_id integer,
  CONSTRAINT saved_route_px PRIMARY KEY (saved_route_id),
  CONSTRAINT saved_route_fk1 FOREIGN KEY (organisation_id)
      REFERENCES overload.organisation (organisation_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.saved_route
  OWNER TO postgres;
COMMENT ON COLUMN overload.saved_route.shortname IS 'Label to easily identify the route';
COMMENT ON COLUMN overload.saved_route.description IS 'More detailed descrition of the route';
COMMENT ON COLUMN overload.saved_route.organisation_id IS 'Owner organisation of the saved route';

CREATE INDEX saved_route_idx1
  ON overload.saved_route
  USING btree
  (organisation_id, shortname);

--- PERMIT

-- Table: overload.permit

CREATE TABLE overload.permit
(
  permit_id serial NOT NULL,
  permit_number character varying(15),
  effective_from timestamp with time zone,
  effective_to timestamp with time zone,
  document_link character varying(255),
  CONSTRAINT permit_px PRIMARY KEY (permit_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.permit OWNER TO postgres;

COMMENT ON COLUMN overload.permit.permit_number IS 'UUID for permit and must include a check digit';
COMMENT ON COLUMN overload.permit.effective_from IS 'Start date of permit. May not be what was requested.';
COMMENT ON COLUMN overload.permit.effective_to IS 'End Date of permit, will be calculated but may be overridden by suitabley authorised personel';
COMMENT ON COLUMN overload.permit.document_link IS 'Link to stored permit PDF, can only be regenerated by managers with regenerate function';

-- Table: overload.condition

CREATE TABLE overload.condition
(
  condition_id serial NOT NULL,
  condition character varying(255),
  CONSTRAINT condition_pk PRIMARY KEY (condition_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.condition OWNER TO postgres;

-- Table: overload.travel_conditions

CREATE TABLE overload.travel_conditions
(
  travel_conditions_id serial NOT NULL,
  permit_id integer NOT NULL,
  conditions_seq integer NOT NULL,
  condition_id integer,
  free_text_condition character varying(255),
  CONSTRAINT travel_conditions_px PRIMARY KEY (travel_conditions_id),
  CONSTRAINT travel_conditions_fk1 FOREIGN KEY (permit_id)
      REFERENCES overload.permit (permit_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT travel_conditions_fk2 FOREIGN KEY (condition_id)
      REFERENCES overload.condition (condition_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.travel_conditions OWNER TO postgres;
COMMENT ON COLUMN overload.travel_conditions.condition_id IS 'Preset conditions';
COMMENT ON COLUMN overload.travel_conditions.free_text_condition IS 'Free text conditions';

--- Vehicles

-- Table: overload.dimension

CREATE TABLE overload.dimension
(
  dimension_id serial NOT NULL,
  length numeric(10,2),
  width numeric(10,2),
  height numeric(10,2),
  front_overhang numeric(10,2),
  rear_overhang numeric(10,2),
  front_projection numeric(10,2),
  rear_projection numeric(10,2),
  lhs_projection numeric(10,2),
  rhs_projection numeric(10,2),
  CONSTRAINT dimension_px PRIMARY KEY (dimension_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.dimension OWNER TO postgres;

-- Table: overload.load

CREATE TABLE overload.load
(
  load_id serial NOT NULL,
  load_mass numeric(10,2),
  load_type_id integer,
  load_description character varying(30),
  CONSTRAINT load_px PRIMARY KEY (load_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.load OWNER TO postgres;

-- Table: overload.axle_configuration

CREATE TABLE overload.axle_configuration
(
  axle_configuration_id serial NOT NULL,
  axle_group integer,
  number_of_axles integer,
  gross_mass numeric(10,2),
  CONSTRAINT axle_configuration_px PRIMARY KEY (axle_configuration_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.axle_configuration OWNER TO postgres;

-- Table: overload.axle

CREATE TABLE overload.axle
(
  axle_id serial NOT NULL,
  axle_configuration_id integer NOT NULL,
  axle_number integer NOT NULL,
  mass numeric(10,2),
  tyres integer,
  tyre_width integer,
  ground_contact_length numeric(10,2),
  ground_contact_width numeric(10,2),
  CONSTRAINT axle_px PRIMARY KEY (axle_id),
  CONSTRAINT axle_fk1 FOREIGN KEY (axle_configuration_id)
      REFERENCES overload.axle_configuration (axle_configuration_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.axle OWNER TO postgres;

COMMENT ON COLUMN overload.axle.mass IS 'Requested or Permitted Mass';
COMMENT ON COLUMN overload.axle.tyres IS 'Tyres per axle, must be even number';
COMMENT ON COLUMN overload.axle.tyre_width IS 'Manufacturers tyre width';
COMMENT ON COLUMN overload.axle.ground_contact_length IS 'Distance between axle centres (also called axle spacing)';
COMMENT ON COLUMN overload.axle.ground_contact_width IS 'Distance between outer most tyres on axle';

CREATE INDEX axle_idx1
  ON overload.axle
  USING btree
  (axle_configuration_id);

-- Table: overload.vehicle

CREATE TABLE overload.vehicle
(
  vehicle_id serial NOT NULL,
  registration_plate character varying(10),
  state_of_issue_id integer,
  vehicle_identfication_number character varying(20),
  chassis character varying(20),
  engine character varying(20),
  external_reference character varying(30),
  gross_vehicle_mass numeric(10,2),
  gross_combination_mass numeric(10,2),
  aggregate_trailer_mass numeric(10,2),
  tare numeric(10,2),
  axles smallint,
  steer_axles smallint,
  drive_axles smallint,
  approved boolean,
  organisation_id integer,
  CONSTRAINT vehicle_px PRIMARY KEY (vehicle_id),
  CONSTRAINT vehicle_fk1 FOREIGN KEY (organisation_id)
      REFERENCES overload.organisation (organisation_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.vehicle OWNER TO postgres;
COMMENT ON COLUMN overload.vehicle.tare IS 'Unladen Weight';
COMMENT ON COLUMN overload.vehicle.approved IS 'ATO ?';

CREATE INDEX vehicle_idx1
  ON overload.vehicle
  USING btree
  (organisation_id, vehicle_id);

COMMENT ON INDEX overload.vehicle_idx1 IS 'Provides the vehicle fleet for an organisation';

-- Table: overload.vehicle_list

CREATE TABLE overload.vehicle_list
(
  vehicle_list_id serial NOT NULL,
  vehicle_combination_id integer NOT NULL,
  vehicle_id integer NOT NULL,
  "position" integer NOT NULL,
  CONSTRAINT vehicle_list_px PRIMARY KEY (vehicle_list_id),
  CONSTRAINT vehicle_list_fk1 FOREIGN KEY (vehicle_id)
      REFERENCES overload.vehicle (vehicle_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.vehicle_list  OWNER TO postgres;
COMMENT ON TABLE overload.vehicle_list IS 'A list of each vehicle in the vehicle combination.  Each vehicle in the combination is uniquely identifable and the sequence of vehicles is alos used to determine if a permit can be system issued,';

-- Table: overload.vehicle_combination

CREATE TABLE overload.vehicle_combination
(
  vehicle_combination_id serial NOT NULL,
  vehicle_list_id integer NOT NULL,
  dimension_id integer,
  axle_configuration_id integer,
  road_access integer,
  CONSTRAINT vehicle_combination_px PRIMARY KEY (vehicle_combination_id),
  CONSTRAINT vehicle_combination_fk1 FOREIGN KEY (vehicle_list_id)
      REFERENCES overload.vehicle_list (vehicle_list_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT vehicle_combination_fk2 FOREIGN KEY (dimension_id)
      REFERENCES overload.dimension (dimension_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT vehicle_combination_fk3 FOREIGN KEY (axle_configuration_id)
      REFERENCES overload.axle_configuration (axle_configuration_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.vehicle_combination OWNER TO postgres;
COMMENT ON TABLE overload.vehicle_combination
  IS 'A vehicle combination consists of ONE or more vehicles.  When applying for a permit  the "vehicle builder" function/UI must only dfsplay and validate combinations.  This avoids special cases like big cranes that consist of two vehicles the SPV and the Dolly.';
COMMENT ON COLUMN overload.vehicle_combination.road_access IS 'Road access level for the vehicle.  Needs to match every road segment access level(s) otherwise cannot system issue/special assessment required.';


-- Table: overload.model_vehicle

CREATE TABLE overload.model_vehicle
(
  model_vehicle_id serial NOT NULL,
  name text,
  description text,
  vehicle_combination_id integer, -- Predefined attrbutes of model vehicle
  CONSTRAINT model_vehicle_px PRIMARY KEY (model_vehicle_id),
  CONSTRAINT model_vehicle_fk1 FOREIGN KEY (vehicle_combination_id)
      REFERENCES overload.vehicle_combination (vehicle_combination_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.model_vehicle OWNER TO postgres;

COMMENT ON TABLE overload.model_vehicle
  IS 'Pre-defined model vehicles.  Used primarily in route analysis for consent applications';
COMMENT ON COLUMN overload.model_vehicle.name IS 'Sort name of MCVe.g. B-Double (19), BAB, Road Train-type 1';
COMMENT ON COLUMN overload.model_vehicle.description IS 'Description of rules to deteermine vehicle type';
COMMENT ON COLUMN overload.model_vehicle.vehicle_combination_id IS 'Predefined attrbutes of model vehicle';


--- Applications

-- Table: overload.application

CREATE TABLE overload.application
(
  application_id serial NOT NULL,
  application_type integer,
  application_description text,
  applicant_id integer,
  organisation_id integer,
  coverage_from  timestamp with time zone,
  coverage_to timestamp with time zone,
  extra hstore,
  status_id integer,
  permit_id integer,
  locked boolean,
  locked_by integer,
  creation_date timestamp with time zone,
  created_by integer,
  modification_date timestamp with time zone,
  modified_by integer,
  CONSTRAINT application_px PRIMARY KEY (application_id),
  CONSTRAINT application_fk1 FOREIGN KEY (applicant_id)
      REFERENCES overload."user" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_fk2 FOREIGN KEY (organisation_id)
      REFERENCES overload.organisation (organisation_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_fk3 FOREIGN KEY (permit_id)
      REFERENCES overload.permit (permit_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION)
  WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.application OWNER TO postgres;

COMMENT ON TABLE overload.application IS 'Used as a summary of ALL applications (customer, vehicle, route etc).  Used as the basis of the search queue.';
COMMENT ON COLUMN overload.application.application_id IS 'Incremenatal application #, must be in sequence regardless of application type.';
COMMENT ON COLUMN overload.application.application_type IS 'Application tyre entered, decode from lookup table.';
COMMENT ON COLUMN overload.application.application_description IS 'Generated User-friendly description for the application, so a user can more-easily recognise.';
COMMENT ON COLUMN overload.application.coverage_from IS 'Coverage start date of permit (customer=Issue Date, Vehicle=Issue Date, Route and Areas=User requested but may be changed during issuance';
COMMENT ON COLUMN overload.application.coverage_to IS 'Date coveage expiries. Most often determined by Legislation but could be overwritten by authorised user.';
COMMENT ON COLUMN overload.application.organisation_id IS 'Owner organisation for this application. Used mainly to improve performance on queue searches - customers can only "see" their own applications.';
COMMENT ON COLUMN overload.application.applicant_id IS 'Authenticated User who submitted application. Links to organisation/operator.';
COMMENT ON COLUMN overload.application.extra IS 'Used to store extra customer specific information';
COMMENT ON COLUMN overload.application.status_id IS 'Status of the application.  Used primarily to determine workflows';
COMMENT ON COLUMN overload.application.permit_id IS 'Link to Permit, may change if revised or revoked';
COMMENT ON COLUMN overload.application.locked_by IS 'User of person with applicaton open, not sure if this be best method';

COMMENT ON COLUMN overload.application.created_by IS 'User of most recent person who committed changes';
COMMENT ON COLUMN overload.application.creation_date IS 'Auditing. User who created the application. (usually applicant by may be PMO officer.)';
COMMENT ON COLUMN overload.application.modified_by IS 'Auditing. User of most recent person who commited changes';
COMMENT ON COLUMN overload.application.modification_date IS 'Auditing. User who created the application. (usually as applicant by may be PMO officer.)';

--- The following indexes are created for performance during searches (usually on the queue).
--- The application_id is appended to all indexes so the "default" sort order is application id

CREATE INDEX application_idx1
  ON overload.application
  USING btree
  (application_type, application_id);

CREATE INDEX application_idx2

  ON overload.application
  USING btree
  (applicant_id, application_id);

CREATE INDEX application_idx3
  ON overload.application
  USING btree
  (status_id, application_id);

CREATE INDEX application_idx4
  ON overload.application
  USING btree
  (organisation_id, application_id);

CREATE INDEX application_idx5
  ON overload.application
  USING btree
  (coverage_from, coverage_to, application_id);



--- Not sure about the following.  May need a use case (primarily based on search performance).

/*CREATE INDEX application_idxA
  ON overload.application
  USING btree
  (created_by, application_id);  

CREATE INDEX application_idxB
  ON overload.application
  USING btree
  (creation_date, application_id);  

CREATE INDEX application_idxC
  ON overload.application
  USING btree
  (modified_by, application_id);  

CREATE INDEX application_idxD
  ON overload.application
  USING btree
  (modification_date, application_id);  
*/

-- Customer Application (Self-Registration)


CREATE TABLE overload.application_customer
(
  application_id integer NOT NULL,
  business_contact_id integer,
  business_address_id integer,
  postal_address_id integer,
  delegate_id integer,
  administrator_id integer,
  CONSTRAINT application_customer_pk PRIMARY KEY (application_id),
  CONSTRAINT application_customer_fk1 FOREIGN KEY (application_id)
      REFERENCES overload.application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_customer_fk2 FOREIGN KEY (business_contact_id)
      REFERENCES overload.contact (contact_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_customer_fk3 FOREIGN KEY (business_address_id)
      REFERENCES overload.address (address_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_customer_fk4 FOREIGN KEY (postal_address_id)
      REFERENCES overload.address (address_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_customer_fk5 FOREIGN KEY (delegate_id)
      REFERENCES overload.contact (contact_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_customer_fk6 FOREIGN KEY (administrator_id)
      REFERENCES overload."user" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.application_customer  OWNER TO postgres;

COMMENT ON TABLE overload.application IS 'Customer self-registration application, mainly holds business information.';
COMMENT ON COLUMN overload.application_customer.application_id IS 'Primary Key, one to one link with the application master table.';
COMMENT ON COLUMN overload.application_customer.business_contact_id IS 'Contact details of a person who is first point of contact for general business.'; 
COMMENT ON COLUMN overload.application_customer.business_address_id IS 'Address where business oprators from.';
COMMENT ON COLUMN overload.application_customer.postal_address_id IS 'Address for any standard mail correspondence.';
COMMENT ON COLUMN overload.application_customer.delegate_id IS 'Person (Contact)responsbile for the business operations.  Usually called in the defendant in court.'; 

-- Table: overload.application_vehicle

CREATE TABLE overload.application_vehicle
(
  application_id integer NOT NULL,
  vehicle_combination_id integer,
  CONSTRAINT application_vehicle_px PRIMARY KEY (application_id),
  CONSTRAINT application_vehicle_fk1 FOREIGN KEY (application_id)
      REFERENCES overload.application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_vehicle_fk2 FOREIGN KEY (vehicle_combination_id)
      REFERENCES overload.vehicle_combination (vehicle_combination_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.application_vehicle OWNER TO postgres;

COMMENT ON TABLE overload.application_vehicle IS 'Vehicle Authorisation application. Some vehicles need authoristion before being used in Heavy Haulage operations.';
COMMENT ON COLUMN overload.application_vehicle.vehicle_combination_id IS 'Vehcile (combination) for whihc the applicatn is requesting authorisation';

CREATE INDEX fki_application_vehicle_fk1
  ON overload.application_vehicle
  USING btree
  (vehicle_combination_id, application_id);

-- Table: overload.application_route_permit

CREATE TABLE overload.application_route
(
  application_id integer NOT NULL,
  vehicle_combination_id integer,
  load_id integer,
  route_id integer,
  CONSTRAINT application_route_px PRIMARY KEY (application_id),
  CONSTRAINT application_route_fk1 FOREIGN KEY (application_id)
      REFERENCES overload.application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_route_fk2 FOREIGN KEY (load_id)
      REFERENCES overload.load (load_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_route_fk3 FOREIGN KEY (vehicle_combination_id)
      REFERENCES overload.vehicle_combination (vehicle_combination_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_route_fk4 FOREIGN KEY (route_id)
      REFERENCES overload.route (route_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.application_route OWNER TO postgres;

COMMENT ON TABLE overload.application_route IS 'Application based on route (i.e. single-trip).';
COMMENT ON COLUMN overload.application_route.application_id IS 'Primary Key, one to one link with the application master table.';
COMMENT ON COLUMN overload.application_route.vehicle_combination_id IS 'Specification of entire vehicle combination (may just be one vehicle e.g. SPVs)'; 
COMMENT ON COLUMN overload.application_route.load_id IS 'Specification of load.';
COMMENT ON COLUMN overload.application_route.route_id IS 'Specification of route to be taken.';

-- Not sure if this will be used but it there anyway. Could be useful for finding all applications for a vehicle combination
CREATE INDEX application_route_idx1
  ON overload.application_route
  USING btree
  (vehicle_combination_id, application_id);

-- Not sure if this will be used but it there anyway. All loads should be specific to each application.

CREATE INDEX application_route_idx2
  ON overload.application_route
  USING btree
  (load_id, application_id);

-- Not sure if this will be used but it there anyway.  Could be useful for finding all applications using a particular route....maybe
CREATE INDEX application_route_idx3
  ON overload.application_route
  USING btree
  (route_id, application_id);
  

-- Table: overload.application_area

CREATE TABLE overload.application_area
(
  application_id integer NOT NULL,
  vehicle_combination_id integer,
  load_id integer,
  area_of_operation_id integer,
  CONSTRAINT application_area_px PRIMARY KEY (application_id),
  CONSTRAINT application_area_fk1 FOREIGN KEY (application_id)
      REFERENCES overload.application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_area_fk2 FOREIGN KEY (vehicle_combination_id)
      REFERENCES overload.vehicle_combination (vehicle_combination_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_area_fk3 FOREIGN KEY (load_id)
      REFERENCES overload.load (load_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_area_fk4 FOREIGN KEY (area_of_operation_id)
      REFERENCES overload.area_of_operation (area_of_operation_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.application_area OWNER TO postgres;

COMMENT ON TABLE overload.application_area IS 'Application based on travel within an area(s) over an extended period.';
COMMENT ON COLUMN overload.application_area.application_id IS 'Primary Key, one to one link with the application master table.';
COMMENT ON COLUMN overload.application_area.vehicle_combination_id IS 'Specification of entire vehicle combination (may just be one vehicle e.g. SPVs)'; 
COMMENT ON COLUMN overload.application_area.load_id IS 'Specification of load.';
COMMENT ON COLUMN overload.application_area.area_of_operation_id IS 'Specification of predefined area travel is being requested.';

-- Table: overload.application_road_manager_consent

CREATE TABLE overload.application_road_manager_consent
(
  application_id integer NOT NULL,
  model_vehicle_id integer,
  load_id integer,
  route_id integer,
  CONSTRAINT application_road_manager_consent_px PRIMARY KEY (application_id),
  CONSTRAINT application_road_manager_consent__fk1 FOREIGN KEY (application_id)
      REFERENCES overload.application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_road_manager_consent__fk2 FOREIGN KEY (model_vehicle_id)
      REFERENCES overload.model_vehicle (model_vehicle_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_road_manager_consent__fk3 FOREIGN KEY (load_id)
      REFERENCES overload.load (load_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT application_road_manager_consent_fk4 FOREIGN KEY (route_id)
      REFERENCES overload.route (route_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.application_road_manager_consent OWNER TO postgres;

COMMENT ON TABLE overload.application_road_manager_consent IS 'Application based on travel of pre-defined vehicles on pre-defined routes.';
COMMENT ON COLUMN overload.application_road_manager_consent.application_id IS 'Primary Key, one to one link with the application master table.';
COMMENT ON COLUMN overload.application_road_manager_consent.model_vehicle_id IS 'Pre-defined vehicle combination (cannot be altered by users)'; 
COMMENT ON COLUMN overload.application_road_manager_consent.load_id IS 'Specification of load.';
COMMENT ON COLUMN overload.application_road_manager_consent.route_id IS 'Specification of route to be taken.';

--- Processing / workflow

-- Table: overload.restriction

CREATE TABLE overload.restriction
(
  restriction_id serial NOT NULL,
  max_length numeric(10,3),
  max_width numeric(10,3),
  max_height numeric(10,3),
  opp_max_width numeric(10,3),
  opp_max_height numeric(10,3),
  max_bending_moment numeric(10,3),
  max_shear numeric(10,3),
  direction character(1),
  the_geom geometry(Geometry,4326),
  CONSTRAINT restriction_pk PRIMARY KEY (restriction_id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.restriction OWNER TO postgres;

COMMENT ON TABLE overload.restriction
  IS 'Restictions on the route.   May include 
Error - No Access (restrictions e.g. road-closed),  
WARNING - travel restrictions (i.e. left-hand 10KM per hour) and 
INFO - informational (Contact police office to organise escorting).

Use geospatial functions to determine if restriction will impact requested route. ';
COMMENT ON COLUMN overload.restriction.max_length IS 'Usually applies to rail-crossings, but possibly on some turns';
COMMENT ON COLUMN overload.restriction.max_width IS 'Width restriction in conventional direction';
COMMENT ON COLUMN overload.restriction.max_height IS 'Height restiction in conventional direction';
COMMENT ON COLUMN overload.restriction.opp_max_width IS 'Width restriction in opposite direction';
COMMENT ON COLUMN overload.restriction.opp_max_height IS 'Height restriction in opposite direction';
COMMENT ON COLUMN overload.restriction.max_bending_moment IS 'Maximum bending moment allowed e.g. bridge span';
COMMENT ON COLUMN overload.restriction.max_shear IS 'Maximum shear allowed. e.g. bridge pier';
COMMENT ON COLUMN overload.restriction.direction IS 'Direction of travel, may not be required as determined from origin=source OR origin=target';
COMMENT ON COLUMN overload.restriction.the_geom IS 'Point for TMS, but may be line in case of road/bridge span';

-- Table: overload.review

CREATE TABLE overload.review
(
  review_id serial NOT NULL,
  application_id integer NOT NULL,
  assigned_to integer NOT NULL,
  assigned_on timestamp with time zone,
  actioned_on timestamp with time zone,
  actioned_by integer,
  review_status integer,
  last_reminder timestamp with time zone,
  CONSTRAINT review_px PRIMARY KEY (review_id),
  CONSTRAINT review_fk1 FOREIGN KEY (application_id)
      REFERENCES overload.application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT review_fk2 FOREIGN KEY (assigned_to)
      REFERENCES overload.organisation (organisation_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT review_fk3 FOREIGN KEY (actioned_by)
      REFERENCES overload."user" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.review OWNER TO postgres;

COMMENT ON TABLE overload.review IS 'Management of specialist reviewers (aka special assessment.';
COMMENT ON COLUMN overload.review.application_id IS 'Application being reviewed. Will always be the latest ?';
COMMENT ON COLUMN overload.review.assigned_to IS 'Reviewer organisation. Reviwers must be part of this organisation to action.';
COMMENT ON COLUMN overload.review.assigned_on IS 'Date time sent for review.  Another review will require a new record';
COMMENT ON COLUMN overload.review.actioned_by IS 'User who actioned.  Will be blank if recalled by PMO';
COMMENT ON COLUMN overload.review.actioned_on IS 'Date time review was actioned.';
COMMENT ON COLUMN overload.review.last_reminder IS 'Date time last reminder was sent.  Dont keep a history of reminders';
COMMENT ON COLUMN overload.review.review_status IS 'Status of review. Not to be confused with status of application';

--- Miscellaneous

-- Table: overload.lookup

CREATE TABLE overload.lookup
(
  id serial NOT NULL,
  lookup hstore NOT NULL,
  CONSTRAINT lookup_px PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

--- COMMIT;

ALTER TABLE overload.lookup OWNER TO postgres;

CREATE INDEX lookup_idx1
  ON overload.lookup
  USING gin
  (lookup);


--- COMMIT;

