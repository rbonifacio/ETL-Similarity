import os
import subprocess

rootdir = '/Users/rbonifacio/Documents/GIAPS/etls/adc_stage_saps/'
# rootdir = '/Users/rbonifacio/Documents/GIAPS/etls/adc_stage_saps/setembro_amarelo/'

all_files = []

# collect all files
for subdir, dirs, files in os.walk(rootdir):
    for f in files:
        ext = os.path.splitext(f)[-1].lower()
        if ext == '.xml':
            all_files.append(os.path.join(subdir, f))

count = 0
for f1 in all_files:
    for f2 in all_files:
        
        if f1 != f2:
            count += 1
            # if count < 7120:
            #     continue
        
            if count % 10 == 0:
                print(f"executing comparison {count} of {len(all_files) * len(all_files)}")
            os.system(f"java -jar program.jar {f1} {f2}")
