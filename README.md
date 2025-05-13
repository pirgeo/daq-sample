# Sample application for the DAQ Conference Spring 2025

This is a sample application to showcase the use of OpenTelemetry metrics and the transform and filter capabilities of the collector.
It is intended to show how metrics data can be acquired using OpenTelemetry. 
The generated data needs to be preprocessed to be useful, which is achieved by using an OpenTelemetry collector.

The example consists of three parts: 
- The server application.
- The client application
- The OpenTelemetry collector

## Server
The server represents a very simple coffee shop.
It exposes three HTTP endpoints that can be used to order coffee:
- `/order/cappuccino`
- `/order/espresso`
- `/order/hot-chocolate`

The server uses OpenTelemetry explicitly to count how many beverages are ordered.
On top of that, the Spring Boot autoinstrumentation records many metrics about the application itself.
Examples are: CPU & memory usage, number of HTTP calls by endpoint, JVM GC metrics, and many more.

## Client
The client is a simple app that continuously orders random beverages.
No magic here.

## OTel collector
The [OTel collector](https://opentelemetry.io/docs/collector/) is used to log, filter, transform, and forward the data.
It is a very versatile binary with many configuration options that can be used in a wide variety of cases to ingest business and telemetry data.
The capabilities showcased in this talk only scratch the surface of what is possible with OTel collector.
