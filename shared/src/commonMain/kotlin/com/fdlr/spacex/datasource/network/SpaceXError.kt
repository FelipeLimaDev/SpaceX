package com.fdlr.spacex.datasource.network

enum class SpaceXError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class SpaceXException(val error: SpaceXError): Exception(
    "An error occurred: $error"
)