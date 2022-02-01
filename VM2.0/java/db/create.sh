#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=VendingMachine
export PGPASSWORD=BostonAnn19!
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"