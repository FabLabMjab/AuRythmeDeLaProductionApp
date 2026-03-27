package com.example.aurythmedelaproduction;

public class AssemblyRepository {
    public static AssemblyStep getStep(String assembleurId, String vehicle) {

        if (vehicle.equals("Ski-Doo")) {
            return getSkiDooStep(assembleurId);
        }

        if (vehicle.equals("Spyder")) {
            return getSpyderStep(assembleurId);
        }

        return null;
    }

    private static AssemblyStep getSkiDooStep(String id) {

        switch (id) {

            /******* Les assembleur# et assembleur#A ont le même affichage (jaune)********/
            case "assembleur1":
            case "assembleur1A":
                return new AssemblyStep(R.drawable.assembleur1, R.string.assemblyrepository_assembleur1);


            case "assembleur2":
            case "assembleur2A":
                return new AssemblyStep(R.drawable.assembleur2, R.string.assemblyrepository_assembleur2);


            case "assembleur3":
            case "assembleur3A":
                return new AssemblyStep(R.drawable.assembleur3, R.string.assemblyrepository_assembleur3);


            case "assembleur4":
            case "assembleur4A":
                return new AssemblyStep(R.drawable.assembleur4, R.string.assemblyrepository_assembleur4);


            case "assembleur5":
            case "assembleur5A":
                return new AssemblyStep(R.drawable.assembleur5, R.string.assemblyrepository_assembleur5);


            case "assembleur6":
            case "assembleur6A":
                return new AssemblyStep(R.drawable.assembleur6, R.string.assemblyrepository_assembleur6);


            case "assembleur7":
            case "assembleur7A":
                return new AssemblyStep(R.drawable.assembleur7, R.string.assemblyrepository_assembleur7);


            case "assembleur8":
            case "assembleur8A":
                return new AssemblyStep(R.drawable.assembleur8, R.string.assemblyrepository_assembleur8);


            case "assembleur9":
            case "assembleur9A":
                return new AssemblyStep(R.drawable.assembleur9, R.string.assemblyrepository_assembleur9);


            /******* Les assembleur#B ont un affichage différent (rouge)********/
            case "assembleur1B":
                return new AssemblyStep(R.drawable.assembleur1, R.string.assemblyrepository_assembleur1);


            case "assembleur2B":
                return new AssemblyStep(R.drawable.assembleur2b, R.string.assemblyrepository_assembleur2);


            case "assembleur3B":
                return new AssemblyStep(R.drawable.assembleur3b, R.string.assemblyrepository_assembleur3);


            case "assembleur4B":
                return new AssemblyStep(R.drawable.assembleur4b, R.string.assemblyrepository_assembleur4);


            case "assembleur5B":
                return new AssemblyStep(R.drawable.assembleur5b, R.string.assemblyrepository_assembleur5);


            case "assembleur6B":
                return new AssemblyStep(R.drawable.assembleur6b, R.string.assemblyrepository_assembleur6);


            case "assembleur7B":
                return new AssemblyStep(R.drawable.assembleur7b, R.string.assemblyrepository_assembleur7);


            case "assembleur8B":
                return new AssemblyStep(R.drawable.assembleur8, R.string.assemblyrepository_assembleur8);


            case "assembleur9B":
                return new AssemblyStep(R.drawable.assembleur9, R.string.assemblyrepository_assembleur9);


        }

        return null;
    }

    private static AssemblyStep getSpyderStep(String id) {

        switch (id) {

            case "assembleur1A":
                return new AssemblyStep(R.drawable.assembleur1,R.string.assemblyrepository_assembleur1);

            case "assembleur2A":
                return new AssemblyStep(R.drawable.assembleur2,R.string.assemblyrepository_assembleur2);


            case "assembleur3A":
                return new AssemblyStep(R.drawable.assembleur3,R.string.assemblyrepository_assembleur3);


            case "assembleur4A":
                return new AssemblyStep(R.drawable.spyder_assembleur4,R.string.assemblyrepository_spyder_assembleur4);


            case "assembleur5A":
                return new AssemblyStep(R.drawable.spyder_assembleur5,R.string.assemblyrepository_spyder_assembleur5);


            case "assembleur1B":
                return new AssemblyStep(R.drawable.assembleur1,R.string.assemblyrepository_assembleur1);


            case "assembleur2B":
                return new AssemblyStep(R.drawable.assembleur2b,R.string.assemblyrepository_assembleur2);


            case "assembleur3B":
                return new AssemblyStep(R.drawable.assembleur3b,R.string.assemblyrepository_assembleur3);


            case "assembleur4B":
                return new AssemblyStep(R.drawable.spyder_assembleur4b,R.string.assemblyrepository_spyder_assembleur4);


            case "assembleur5B":
                return new AssemblyStep(R.drawable.spyder_assembleur5b,R.string.assemblyrepository_spyder_assembleur5);


        }

        return null;
    }
}
