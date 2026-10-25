const targetKeys = [
  'DB_PASSWORD',
  'DB_URL',
  'DB_USERNAME',
  'JAVA_TOOL_OPTIONS',
  'JWT_SECRET',
  'LOGGING_LEVEL_COM_ZAXWER_HIKARI',
  'LOGGING_LEVEL_ORG_FLYWAYDB',
  'ML_SERVICE_TOKEN',
  'ML_SERVICE_URL'
];

console.log('\n# ==================== BEGIN DUMPED .ENV ====================');

targetKeys.forEach((key) => {
  let val = process.env[key];

  // Check alternative spelling if the Hikari key had standard single-W spelling
  if (val === undefined && key === 'LOGGING_LEVEL_COM_ZAXWER_HIKARI') {
    val = process.env['LOGGING_LEVEL_COM_ZAXER_HIKARI'];
  }

  if (val !== undefined) {
    // Quote and escape value if it contains spaces, newlines, or quotes
    if (val.includes(' ') || val.includes('\n') || val.includes('"')) {
      val = `"${val.replace(/"/g, '\\"')}"`;
    }
    console.log(`${key}=${val}`);
  } else {
    console.log(`# ${key}=NOT_SET`);
  }
});

console.log('# ===================== END DUMPED .ENV =====================\n');

// Keep process alive briefly so stdout logs flush completely before container exits
setTimeout(() => {
  process.exit(0);
}, 3000);
