# How to run application with newrelic java agent

Please replace `<%= license_key %>` at `newrelic/newrelic.yml` file before starting application. For example:

```
license_key: <license key>
```

Start application with this command below:

```
java -javaagent:newrelic/newrelic.jar -jar target/tix-nr-webflux-1.0.0.1-SNAPSHOT.jar
```

## Newrelic Dashboard

```
tix-nr-webflux
Service | Tiket.com - Dev Env
App ID642935051
Account ID 2381220
Account name Tiket.com - Dev Env
```

[tix-nr-webflux-dashboard-url](https://one.newrelic.com/launcher/nr1-core.explorer?pane=eyJuZXJkbGV0SWQiOiJhcG0tbmVyZGxldHMub3ZlcnZpZXciLCJlbnRpdHlJZCI6Ik1qTTRNVEl5TUh4QlVFMThRVkJRVEVsRFFWUkpUMDU4TmpReU9UTTFNRFV4In0=&sidebars[0]=eyJuZXJkbGV0SWQiOiJucjEtY29yZS5hY3Rpb25zIiwiZW50aXR5SWQiOiJNak00TVRJeU1IeEJVRTE4UVZCUVRFbERRVlJKVDA1OE5qUXlPVE0xTURVeCIsInNlbGVjdGVkTmVyZGxldCI6eyJuZXJkbGV0SWQiOiJhcG0tbmVyZGxldHMub3ZlcnZpZXcifX0=&platform[accountId]=2381220&platform[timeRange][duration]=1800000&platform[$isFallbackTimeRange]=true)