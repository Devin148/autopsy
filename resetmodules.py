import subprocess

def checkout(module):
    subprocess.call(["git", "checkout", "--", module], stdout=subprocess.PIPE)

if __name__ == "__main__":
    modules =  ["Core",
                "KeywordSearch",
                "RecentActivity",
                "branding",
                "ExifParser",
                "HashDatabase",
                "Testing",
                "thunderbirdparser",
                "CoreLibs"]
    for module in modules:
        checkout(module)