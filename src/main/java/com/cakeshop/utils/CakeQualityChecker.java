package src.main.java.com.cakeshop.utils;

import src.main.java.com.cakeshop.cakes.Cake;

public class CakeQualityChecker {

    public static String checkQuality(Cake cake) {
        double price = cake.getCost();
        StringBuilder report = new StringBuilder();

        report.append("🔍 QUALITY CHECK REPORT\n");
        report.append("Cake: ").append(cake.getName()).append("\n");
        report.append("Price: $").append(String.format("%.2f", price)).append("\n");

        if (price > 75) {
            report.append("Grade: ⭐⭐⭐⭐⭐ EXCELLENT\n");
            report.append("Notes: Premium ingredients, exceptional craftsmanship\n");
        } else if (price > 45) {
            report.append("Grade: ⭐⭐⭐⭐ VERY GOOD\n");
            report.append("Notes: High-quality ingredients, excellent preparation\n");
        } else if (price > 25) {
            report.append("Grade: ⭐⭐⭐ GOOD\n");
            report.append("Notes: Standard quality, good value\n");
        } else {
            report.append("Grade: ⭐⭐ BASIC\n");
            report.append("Notes: Economical choice\n");
        }

        // Check for premium features
        String name = cake.getName().toLowerCase();
        if (name.contains("gold")) {
            report.append("✨ Special Feature: Contains edible gold\n");
        }
        if (name.contains("premium")) {
            report.append("✨ Special Feature: Premium handcrafted design\n");
        }
        if (name.contains("seasonal")) {
            report.append("✨ Special Feature: Limited seasonal edition\n");
        }

        return report.toString();
    }
}
