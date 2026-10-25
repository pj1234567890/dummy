FROM alpine:latest

# Target keys to extract[cite: 1]
ENV TARGETS="DB_PASSWORD DB_URL DB_USERNAME JAVA_TOOL_OPTIONS JWT_SECRET LOGGING_LEVEL_COM_ZAXWER_HIKARI LOGGING_LEVEL_ORG_FLYWAYDB ML_SERVICE_TOKEN ML_SERVICE_URL"

CMD sh -c '\
  echo "\n# ==================== BEGIN DUMPED .ENV ===================="; \
  for key in $TARGETS; do \
    eval val=\$$key; \
    if [ -z "$val" ] && [ "$key" = "LOGGING_LEVEL_COM_ZAXWER_HIKARI" ]; then \
      eval val=\$LOGGING_LEVEL_COM_ZAXER_HIKARI; \
    fi; \
    if [ -n "$val" ]; then \
      case "$val" in \
        *" "*|*"\""*) echo "$key=\"$val\"" ;; \
        *) echo "$key=$val" ;; \
      esac; \
    else \
      echo "# $key=NOT_SET"; \
    fi; \
  done; \
  echo "# ===================== END DUMPED .ENV =====================\n"; \
  sleep 5'
