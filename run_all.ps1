# Run All Microservices

$services = @(
    @{ Name = "discovery-service"; Path = "discovery-service"; Port = 8761 },
    @{ Name = "gateway-service";   Path = "gateway-service";   Port = 8888 },
    @{ Name = "customer-service";  Path = "customer-service";  Port = 8081 },
    @{ Name = "inventory-service"; Path = "inventory-service"; Port = 8082 } # Assuming 8082
)

$root = Get-Location

foreach ($service in $services) {
    Write-Host "--------------------------------------------------" -ForegroundColor Cyan
    Write-Host "Processing $($service.Name)..." -ForegroundColor Cyan
    
    $servicePath = Join-Path $root $service.Path
    if (-not (Test-Path $servicePath)) {
        Write-Error "Path not found: $servicePath"
        continue
    }

    Set-Location $servicePath
    
    # Build
    Write-Host "Building $($service.Name)..." -ForegroundColor Yellow
    mvn clean install -DskipTests
    
    if ($LASTEXITCODE -ne 0) {
        Write-Error "Build failed for $($service.Name). Checks logs."
        Read-Host "Press Enter to continue anyway (or Ctrl+C to stop)..."
    }

    # Start in new window
    Write-Host "Starting $($service.Name) on port $($service.Port)..." -ForegroundColor Green
    $javaCmd = "mvn spring-boot:run"
    
    # We use Start-Process to open a new terminal window
    # -Wait is NOT used so we can start the next one
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$servicePath'; $javaCmd"
    
    # Wait a bit for Discovery Service to boot up before starting others
    if ($service.Name -eq "discovery-service") {
        Write-Host "Waiting 20 seconds for Discovery Service to initialize..." -ForegroundColor Magenta
        Start-Sleep -Seconds 20
    } else {
        Start-Sleep -Seconds 5
    }
}

Set-Location $root
Write-Host "--------------------------------------------------" -ForegroundColor Cyan
Write-Host "All services startup commands issued." -ForegroundColor Cyan
Write-Host "Please check the individual windows for status." -ForegroundColor Cyan
