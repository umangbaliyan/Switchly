# Switchlyy

# 

# ASSIGNMENT FOR SESSION 2

# 

# Add a description field to Flag. Optional when creating. Which files did you have to touch — and which didn't you? The field should be optional when creating a flag.

# Add DELETE /api/v1/flags/{flagId}. Return 204 No Content. Deleting a flag that doesn't exist should return 404.

# Files Touched

# 

# 1\. Controller Layer — FlagController.java

# 

# Change: Passed the optional description from the request to the service.

# Location: Line 38

# 

# return flagService.create(

# &#x20;   projectId,

# &#x20;   request.key(),

# &#x20;   request.name(),

# &#x20;   request.description()

# );

# 

# 2\. DTO Layer — CreateFlagRequest.java

# 

# Change: Added the optional description field to the request record.

# Location: Line 13

# 

# String description

# 

# Note: No validation annotation was added because the description is optional.

# 

# 3\. Model Layer — Flag.java

# 

# Change: Added the description field, constructor parameter, assignment, and getter.

# 

# Locations:

# 

# Line 10: Description field

# Line 18: Constructor parameter

# Line 25: Assigns description

# 

# Line 47: Getter

# 

# private final String description;

# 

# public Flag(UUID id, UUID projectId, String key, String name, String description) {

# this.description = description;

# }

# 

# public String getDescription() {

# return description;

# }

# 

# 4\. Service Layer — FlagService.java

# 

# Change: Updated the create() method to accept the optional description and pass it to the Flag model.

# 

# Locations:

# 

# Line 20: Added description parameter

# 

# Line 33: Passed description to the Flag constructor

# 

# public Flag create(UUID projectId, String key, String name, String description) {

# Flag flag = new Flag(

# UUID.randomUUID(),

# project.getOrganizationId(),

# project.getId(),

# key,

# name,

# description,

# false

# );

# 

# SCREENSHOTS

# 

# 1\. Create Organization

# 

# 2\. Create Project

# 

# 3\. Create Flag

# 

# 4\. Turn Flag ON

# 

# 5\. Check Flag Status

# 

# 6\. Delete Flag

# 

# 3\. Think, Don't Code

# 

# A customer wants new-checkout ON in their test environment but OFF for real users.

# 

# What in your current design would have to change?

# 

# The current design stores only one enabled value for each flag. Therefore, if new-checkout is turned ON, it is ON for everyone, and if it is turned OFF, it is OFF for everyone.

# 

# To support new-checkout being ON in the test environment but OFF for real users, the design would need environment-specific flag states.

# 

# The Flag model would need to associate an enabled/disabled state with environments such as test and production.

# 

# The repository and service would need to retrieve and update the state for a specific environment, and the API would need to accept the environment when checking or changing a flag.

# 

# Conceptually:

# 

# new-checkout → test → ON

# new-checkout → production → OFF

# 

# The current single enabled field would therefore need to be replaced or extended with environment-specific state management.

