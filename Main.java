public class Main {

    public static class Report {
        private final String title;
        private final String author;
        private final String content;
        private final String format;
        private final boolean includeCharts;

        public Report(Builder builder, String format) {
            this.title = builder.title;
            this.author = builder.author;
            this.content = builder.content;
            this.format = format;
            this.includeCharts = builder.includeCharts;
        }

        public void displayReport() {
            System.out.println("=== REPORT (" + format + ") ===");
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Charts: " + includeCharts);
            System.out.println("Content:\n" + content + "\n");
        }

        public static class Builder {
            private String title;
            private String author = "Anonymous";
            private String content = "";
            private boolean includeCharts = false;

            public Builder title(String title) {
                this.title = title;
                return this;
            }

            public Builder author(String author) {
                this.author = author;
                return this;
            }

            public Builder content(String content) {
                this.content = content;
                return this;
            }

            public Builder includeCharts(boolean includeCharts) {
                this.includeCharts = includeCharts;
                return this;
            }

            public Report buildPdf() {
                validate();
                return new Report(this, "PDF");
            }

            public Report buildHtml() {
                validate();
                return new Report(this, "HTML");
            }

            private void validate() {
                if (title == null || title.trim().isEmpty()) {
                    throw new IllegalStateException("Validation error: 'title' is required.");
                }
            }
        }
    }

    public static class ReportDirector {
        public Report constructFinancialPdf(Report.Builder builder) {
            return builder.title("Quarterly Financial Summary")
                    .author("Reim")
                    .content("Revenue grew by 15% in Q3.")
                    .includeCharts(true)
                    .buildPdf();
        }

        public Report constructQuickHtml(Report.Builder builder) {
            return builder.title("Quick Status Update")
                    .content("All systems operational.")
                    .buildHtml();
        }
    }

    public static void main(String[] args) {
        // Тікелей Builder арқылы жасау
        Report pdfReport = new Report.Builder()
                .title("Annual Report")
                .author("Reim")
                .content("Detailed yearly analysis.")
                .includeCharts(true)
                .buildPdf();
        pdfReport.displayReport();
        
        ReportDirector director = new ReportDirector();
        Report financialReport = director.constructFinancialPdf(new Report.Builder());
        financialReport.displayReport();

        Report htmlReport = director.constructQuickHtml(new Report.Builder());
        htmlReport.displayReport();
    }
}
