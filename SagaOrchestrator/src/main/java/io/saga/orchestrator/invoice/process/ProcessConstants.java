package io.saga.orchestrator.invoice.process;

public class ProcessConstants {

    public static final String VAR_CTX = "context";
    public static final String INVOICE = "invoice";
    public static final String VAR_RESPONSE = "response";

    public static final String SERVICE_NAME_CREATE_INVOICE_EXECUTE = "CreateInvoiceService.execute";
    public static final String ACTIVITY_NAME_CREATE_INVOICE_EXECUTE = "createInvoiceActivity";

    public static final String SERVICE_NAME_CREATE_INVOICE_COMPENSATION = "CreateInvoiceService.compensation";
    public static final String ACTIVITY_NAME_CREATE_INVOICE_COMPENSATION = "createInvoiceCompensationActivity";

    public static final String SERVICE_NAME_BOOK_ITEMS_EXECUTE = "BookItemsService.execute";
    public static final String ACTIVITY_NAME_BOOK_ITEMS_EXECUTE = "bookItemsActivity";

    public static final String SERVICE_NAME_BOOK_ITEMS_COMPENSATION = "BookItemsService.compensation";
    public static final String ACTIVITY_NAME_BOOK_ITEMS_COMPENSATION = "bookItemsCompensationActivity";

    public static final String SERVICE_NAME_PAY_INVOICE_EXECUTE = "PayInvoiceService.execute";
    public static final String ACTIVITY_NAME_PAY_INVOICE_EXECUTE = "payInvoiceActivity";

    public static final String SERVICE_NAME_PAY_INVOICE_COMPENSATION = "PayInvoiceService.compensation";
    public static final String ACTIVITY_NAME_PAY_INVOICE_COMPENSATION = "payInvoiceCompensationActivity";

    public static final String SERVICE_NAME_TICKET_GENERATION_EXECUTE = "TicketGenerationService.execute";
    public static final String ACTIVITY_NAME_TICKET_GENERATION_EXECUTE = "ticketGenerationActivity";

    public static final String SERVICE_NAME_TICKET_GENERATION_COMPENSATION = "TicketGenerationService.compensation";
    public static final String ACTIVITY_NAME_TICKET_GENERATION_COMPENSATION = "ticketGenerationCompensationActivity";


    public static final String STATUS_INVOICE_PLACED = "invoicePlaced";
    public static final String STATUS_ITEMS_BOOKED = "itemsBooked";
    public static final String STATUS_PAYMENT_EXECUTED = "paymentExecuted";
    public static final String STATUS_TICKET_GENERATED = "ticketGenerated";



    public static final String UNKNOWN = "UNKNOWN";

    private ProcessConstants() {
    }
}
