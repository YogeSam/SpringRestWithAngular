package uri;

public interface IBookURI {
	//Defined Views
	static final String VIEW_ADDBOOKURI = "/bookAdd.jsp?";
	static final String VIEW_LISTBOOKURI = "/book.jsp?Op=List";
	static final String VIEW_UPDATEBOOKURI = "/bookUpdate.jsp?";
	static final String VIEW_ERRORURI = "/unknown.jsp?";

	//Defined controllers
	static final String CTRL_ADDBOOKURI = "/Book/Add";
	static final String CTRL_UPDATEBOOKURI = "/Book/Update";
	static final String CTRL_DELBOOKURI = "/Book/Delete";
	static final String CTRL_SAVEBOOKURI = "/Book/Save";
	static final String CTRL_LISTBOOKURI = "/Book";
	
}
