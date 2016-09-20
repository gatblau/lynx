#!/usr/bin/env bash
# changes the user to mysql(997)
chown -R 997 $1

# changes the group to root
chgrp -R 0 $1

# allow group read / write operations
chmod -R g+rw $1

# allow execute of directory
find $1 -type d -exec chmod g+x {} +
