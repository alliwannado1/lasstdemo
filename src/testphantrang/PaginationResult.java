package testphantrang;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

public class PaginationResult<E> {

	private int tongBanGhi;
	private int trangHienTai;
	private List<E> list;
	private int trangLonNhat;
	private int tongSoTrang;

	private int maxNavigationPage;

	private List<Integer> navigationPages;

	public PaginationResult(Query<E> query, int page, int maxResult, int maxNavigationPage) {
		final int pageIndex = page - 1 < 0 ? 0 : page - 1;

		int fromRecordIndex = pageIndex * maxResult;
		int maxRecordIndex = fromRecordIndex + maxResult;

		ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);

		List<E> results = new ArrayList<E>();

		boolean hasResult = resultScroll.first();

		if (hasResult) {

			// Scroll to position:
			hasResult = resultScroll.scroll(fromRecordIndex);

			if (hasResult) {
				do {
					E record = (E) resultScroll.get(0);
					results.add(record);
				} while (resultScroll.next()//
						&& resultScroll.getRowNumber() >= fromRecordIndex
						&& resultScroll.getRowNumber() < maxRecordIndex);

			}

			// Go to Last record.
			resultScroll.last();
		}
		
		// Total Record
		
		this.tongBanGhi = resultScroll.getRowNumber() + 1;
		this.trangHienTai = pageIndex + 1;
		this.list = results;
		this.trangLonNhat = maxResult;
		
		
		if (this.tongBanGhi % this.trangLonNhat == 0) {
			this.tongSoTrang = this.tongBanGhi / this.trangLonNhat;
		}else {
			this.tongSoTrang = (this.tongBanGhi / this.trangLonNhat) + 1;
		}
		
		this.maxNavigationPage = maxNavigationPage;
		
		if (maxNavigationPage < tongBanGhi) {
			this.maxNavigationPage = maxNavigationPage;
		}
		
		resultScroll.close();
		
		
	}

	public int getMaxNavigationPage() {
		return maxNavigationPage;
	}

	public void setMaxNavigationPage(int maxNavigationPage) {
		this.maxNavigationPage = maxNavigationPage;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}

	public int getTongBanGhi() {
		return tongBanGhi;
	}

	public void setTongBanGhi(int tongBanGhi) {
		this.tongBanGhi = tongBanGhi;
	}

	public int getTrangHienTai() {
		return trangHienTai;
	}

	public void setTrangHienTai(int trangHienTai) {
		this.trangHienTai = trangHienTai;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getTrangLonNhat() {
		return trangLonNhat;
	}

	public void setTrangLonNhat(int trangLonNhat) {
		this.trangLonNhat = trangLonNhat;
	}

	public int getTongSoTrang() {
		return tongSoTrang;
	}

	public void setTongSoTrang(int tongSoTrang) {
		this.tongSoTrang = tongSoTrang;
	}

}
