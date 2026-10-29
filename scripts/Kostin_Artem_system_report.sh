#!/usr/bin/env bash

set -e

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
LOG_FILE="$PROJECT_DIR/logs/Kostin_Artem_system_report.log"

{
    echo "Full name: Artem Kostin"
    echo "Date and time: $(date)"
    echo "System user: $(whoami)"
    echo "Operating system: $(uname -s)"
    echo "Linux kernel version: $(uname -r)"
    echo "CPU information:"
    lscpu
    echo "Memory information:"
    free -h
    echo "Disk usage:"
    df -h
    echo "Available disk space:"
    df -h --output=source,avail,target
} | tee "$LOG_FILE"
